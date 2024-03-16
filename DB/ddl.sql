CREATE TABLE User(
	User_Id int primary key AUTO_INCREMENT,
    Phone_Number CHAR(10) NOT NULL,
    Password NVARCHAR(200) NOT NULL,
    User_Name CHAR(10) NOT NULL,
    Registration_Time DATETIME NOT NULL,
    Last_Login_Time DATETIME
);

CREATE TABLE Book(
     ISBN char(10) primary key,
     Name VARCHAR(30) NOT NULL,
     Author VARCHAR(100) NOT NUlL,
     Introduction NVARCHAR(300)
);

CREATE TABLE Inventory (
Borrowing_Record_Id int primary key AUTO_INCREMENT,
    Inventory_Id int primary key AUTO_INCREMENT,
    ISBN char(13) NOT NULL,
    Store_Time DATETIME NOT NULL,
    Status int NOT NULL,
    ISBN_Id char(13) NOT NULL,
    FOREIGN KEY fk_isbn_id(ISBN_Id)
    REFERENCES Book(ISBN)
);

CREATE TABLE Borrowing_Record(
    Borrowing_Record_Id int primary key AUTO_INCREMENT,
	User_Id int NOT NULL,
	Inventory_Id int NOT NULL,
	Borrowing_Time DATETIME NOT NULL,
	Return_Time DATETIME,
    FOREIGN KEY fk_user_id(User_Id)
    REFERENCES User(User_Id),
	FOREIGN KEY fk_inventory_id(Inventory_Id)
    REFERENCES Inventory(Inventory_Id)
);

