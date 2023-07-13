# Library Project
This project is a web application for managing a library. It is built using the following technologies:

- Spring MVC  
- Spring Security  
- JDBC (PostgresSQL)  
- Jakarta Validation  
- Thymeleaf  

## Features:
The application has two user roles: **Librarian** and **Reader**. Users can select their role during registration.

Librarians can add, edit, and remove books, while Readers can only view and search for books.

To view all the books, go to the /books page. To register, go to the /registration page. To log in, go to the /login page. To log out, go to the /logout page.

Passwords are secured in the database using the **BCryptPasswordEncoder** class.

The search function on the **/books** page allows users to search for books by title, author, or category. New categories can be added on the /books/bookForm page by selecting the corresponding checkbox. This page requires Librarian authority.

#### To get edit and delete button Librarians have access to a “Manage” button on /books page.