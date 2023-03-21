from bs4 import BeautifulSoup
import requests
import sqlite3
import re

def make_list_of_links(weblink):
    website = requests.get(weblink)
    soup = BeautifulSoup(website.content, "html.parser")
    global department_links_global
    rows = soup.find_all('tr')
    
    department_links = []

    for row in rows:
        links = row.find_all('a')
        
        if links:
            department_link = [link['href'] for link in links if link.get('href', '').startswith('/booklet2.nsf/') and link['href'].endswith('OpenDocument')]
            if department_link:
                department_links.extend(department_link)

    department_links_global += department_links

def scrape_data_from_site(url):
    url = "http://appl101.lsu.edu/" + url
    college_page = requests.get(url)
    college_page_soup = BeautifulSoup(college_page.text, features= "html.parser")

    data = college_page_soup.find('pre')
    
    if data:
        parse_classes(data.text)
    
def parse_classes(text):
    global list_of_classes
    classes = []
    lines = text.split('\n')
    
    # Use regex to match lines starting with a number or (F)
    class_pattern = re.compile(r'^(\d+|\(F\))')
    
    for line in lines:
        if class_pattern.match(line):
            parts = line.split()
            class_info = {
                'avl_cnt': parts[0],
                'abbr': parts[1],
                'num': parts[2],
                'type': parts[3],
                'section': parts[4],
                'course_title': ' '.join(parts[5:-7]),
                'credits': parts[-7],
                'time': parts[-6] + '-' + parts[-5],
                'days': parts[-4],
                'room': parts[-3],
                'building': parts[-2],
                'instructor': parts[-1]
            }
            list_of_classes.append(class_info)
# Additional function to create the database and table
def create_database():
    conn = sqlite3.connect('courses.db')
    c = conn.cursor()

    c.execute('''CREATE TABLE IF NOT EXISTS courses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    avl_cnt TEXT,
                    abbr TEXT,
                    num TEXT,
                    type TEXT,
                    section TEXT,
                    course_title TEXT,
                    credits TEXT,
                    time TEXT,
                    days TEXT,
                    room TEXT,
                    building TEXT,
                    instructor TEXT)''')

    conn.commit()
    conn.close()

# Additional function to insert classes into the database
def insert_classes_into_database(classes):
    conn = sqlite3.connect('courses.db')
    c = conn.cursor()

    for course in classes:
        c.execute('''INSERT INTO courses (avl_cnt, abbr, num, type, section, course_title, credits, time, days, room, building, instructor)
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)''', (
                        course['avl_cnt'], 
                        course['abbr'], 
                        course['num'], 
                        course['type'], 
                        course['section'], 
                        course['course_title'], 
                        course['credits'], 
                        course['time'], 
                        course['days'], 
                        course['room'], 
                        course['building'], 
                        course['instructor']))

    conn.commit()
    conn.close()
            
    

url_start = "http://appl101.lsu.edu/"
spring_2023 = "http://appl101.lsu.edu/booklet2.nsf/bed33d8925ab561b8625651700585b85?OpenView&Start=1&Count=100000&Expand=59#59"
summer_2023 = "http://appl101.lsu.edu/booklet2.nsf/bed33d8925ab561b8625651700585b85?OpenView&Start=1&Count=100000&Expand=15#15"
fall_2023 = "http://appl101.lsu.edu/booklet2.nsf/bed33d8925ab561b8625651700585b85?OpenView&Start=1&Count=100000&Expand=115#115"
department_links_global = []
list_of_classes = []
make_list_of_links(spring_2023)
make_list_of_links(summer_2023)
make_list_of_links(fall_2023)

for i in department_links_global:
    scrape_data_from_site(i)
    
create_database()
insert_classes_into_database(list_of_classes)
