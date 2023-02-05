from bs4 import BeautifulSoup
import requests
import csv

starting_page = requests.get("http://appl101.lsu.edu/booklet2.nsf/bed33d8925ab561b8625651700585b85?OpenView&CollapseView")
starting_page_soup = BeautifulSoup(starting_page.text, features = "html.parser")




def grab_semester_url(current_page_soup):#list out urls of courses on that page
    current_table = current_page_soup.find_all('table')
    hold_classes = None
    for i in current_table:
        if i.text.find("SemesterDepartment")>=0:
            hold_classes = i.find_all('a')
            break

    for i in hold_classes:
        print(i['href'])
        print("\n")



def find_next(current_page_soup): #prints the url for the next button on the page
    current_a = current_page_soup.find_all('a')   
    for item in current_a:
        if item.text == "Next":
            print(item.parent.a['href'])
            break

#find_next(starting_page_soup)
#grab_semester_url(starting_page_soup)

