from bs4 import BeautifulSoup
import requests
import sqlite3
import re


def make_list_of_links(weblink, session):
    website = session.get(weblink)
    soup = BeautifulSoup(website.content, "html.parser")
    department_links = []
    rows = soup.find_all('tr')
    for row in rows:
        links = row.find_all('a')
        if links:
            department_link = [link['href'] for link in links if link.get('href', '').startswith('/booklet2.nsf/') and link['href'].endswith('OpenDocument')]
            if department_link:
                department_links.extend(department_link)
                #print(department_link)  
    return department_links


def scrape_data_from_site(url, session):
    url = "http://appl101.lsu.edu/" + url
    college_page = session.get(url)
    college_page_soup = BeautifulSoup(college_page.text, features= "html.parser")
    data = college_page_soup.find('pre')
    list_of_classes = []
    if data:
        lines = data.text.split('\n')
        class_pattern = re.compile(r'^(\d+|\(F\))')
        for line in lines:
            if class_pattern.match(line):
                parts = line.split()
                section_type = re.findall(r'\((.*?)\)', parts[4])
                class_info = {
                    'avl_cnt': parts[0] or '',
                    'enrl_cnt': parts[1] or '',
                    'abbr': parts[2] or '',
                    'num': parts[3] or '',
                    'type': section_type[1] if len(section_type) > 1 else '',
                    'section': section_type[0] if len(section_type) > 0 else '',
                    'course_title': ' '.join(parts[5:-6]) or '',
                    'credits': parts[-6] or '',
                    'time': parts[-5] or '',
                    'days': parts[-4] or '',
                    'room': parts[-3] or '',
                    'building': parts[-2] or '',
                    'special_section': parts[-1] or '',
                    'instructor': ' '.join(parts[-4:-1]) or ''
                }
                list_of_classes.append(class_info)
                print(class_info)  #print class line into console
    return list_of_classes




def create_database():
    conn = sqlite3.connect('courses.db')
    c = conn.cursor()
    c.execute('''CREATE TABLE IF NOT EXISTS courses (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    avl_cnt TEXT,
                    enrl_cnt TEXT,
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
                    special_section TEXT,
                    instructor TEXT)''')
    conn.commit()
    conn.close()


def insert_classes_into_database(classes):
    conn = sqlite3.connect('courses.db')
    c = conn.cursor()
    for course in classes:
        c.execute('''INSERT INTO courses (avl_cnt, enrl_cnt, abbr, num, type, section, course_title, credits, time, days, room, building, special_section, instructor)
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)''', (
                        course['avl_cnt'], 
                        course['enrl_cnt'], 
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
                        course['special_section'],
                        course['instructor']))
    conn.commit()
    conn.close()    
    
def main():
    spring_2023 = "http://appl101.lsu.edu/booklet2.nsf/bed33d8925ab561b8625651700585b85?OpenView&Start=1&Count=100000&Expand=59#59"
    summer_2023 = "http://appl101.lsu.edu/booklet2.nsf/bed33d8925ab561b8625651700585b85?OpenView&Start=1&Count=100000&Expand=15#15"
    fall_2023 = "http://appl101.lsu.edu/booklet2.nsf/bed33d8925ab561b8625651700585b85?OpenView&Start=1&Count=100000&Expand=115#115"

    department_links_global = []
    with requests.Session() as session:
        department_links_global += make_list_of_links(spring_2023, session)
        department_links_global += make_list_of_links(summer_2023, session)
        department_links_global += make_list_of_links(fall_2023, session)

        list_of_classes = []
        for department_link in department_links_global:
            list_of_classes += scrape_data_from_site(department_link, session)

        create_database()
        insert_classes_into_database(list_of_classes)

if __name__ == '__main__':
    main()
