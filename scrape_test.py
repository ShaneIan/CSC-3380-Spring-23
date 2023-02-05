from bs4 import BeautifulSoup
import requests
import csv



starting_page = requests.get("http://appl101.lsu.edu/booklet2.nsf/bed33d8925ab561b8625651700585b85?OpenView&CollapseView")
starting_page_soup = BeautifulSoup(starting_page.text, features = "html.parser")




def sessions_on_current_page(current_page_soup):#list out urls of sesions on that page
    session_url = "http://appl101.lsu.edu"
    current_table = current_page_soup.find_all('table')
    hold_classes = None
   
    for i in current_table:         #grabs table of course offerings on current page
        if i.text.find("SemesterDepartment")>=0:
            hold_classes = i.find_all('a')
            break

    for i in hold_classes:         #link for that a certain course offering  
        session_url +=  i['href']
        course_in_current_session(session_url)
        
        break
def find_next(current_page_soup): #prints the url for the next button on the page
    current_a = current_page_soup.find_all('a')   
    for item in current_a:
        if item.text == "Next":
            print("http://appl101.lsu.edu"+item.parent.a['href'])
            break
def course_in_current_session(url):
    session_page = requests.get(url)
    session_page_soup = BeautifulSoup(session_page.text, features= "html.parser")

    found_url = None   

    tables_on_page = session_page_soup.find_all('table')
    table_of_classes = None

    for i in tables_on_page:
        if i.text.find("SemesterDepartment")>=0:
            table_of_classes = i
            break
    
    for i in table_of_classes.find_all('a'):
        hold = i.text
        if len(hold) != 0:
            found_url = "http://appl101.lsu.edu"+i['href']
            scrape_data_from_booklet(found_url)
            

def scrape_data_from_booklet(url):
    college_page = requests.get(url)
    college_page_soup = BeautifulSoup(college_page.text, features= "html.parser")

    data = college_page_soup.find('pre')
    print(data)

sessions_on_current_page(starting_page_soup)




