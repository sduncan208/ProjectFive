/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * CSCE 156
 */

DROP TABLE IF EXISTS Email_156;
DROP TABLE IF EXISTS Invoice_156;
DROP TABLE IF EXISTS Customer_156;
DROP TABLE IF EXISTS Person_156;
DROP TABLE IF EXISTS Address_156;
DROP TABLE IF EXISTS Concession_156;
DROP TABLE IF EXISTS Rental_156;
DROP TABLE IF EXISTS Repair_156;
DROP TABLE IF EXISTS Tow_156;
DROP TABLE IF EXISTS InvoiceProduct_156;
DROP TABLE IF EXISTS Product_156;
DROP TABLE IF EXISTS Invoice_156;


CREATE TABLE 
IF NOT exists Email_156 
(
emailId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
emailAddress VARCHAR(255) NOT NULL UNIQUE,
personId INT NOT NULL
);

CREATE TABLE 
IF NOT exists Address_156 
(
addressId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
street VARCHAR(100) NOT NULL,
city VARCHAR(85) NOT NULL,
state VARCHAR(100) NOT NULL,
zip VARCHAR(10) NOT NULL,
country VARCHAR(56)
);

CREATE TABLE 
IF NOT exists Person_156 
(
personId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
personCode varchar(255),
firstName VARCHAR(255) NOT NULL,
lastName VARCHAR(255) NOT NULL,
addressId INT NOT NULL,
FOREIGN KEY `fk_Person_156_to_Address_156` (addressId) REFERENCES Address_156(addressId)
);

CREATE TABLE 
IF NOT exists Customer_156 
(
customerId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
customerCode VARCHAR(255),
customerType VARCHAR(25),
customerName VARCHAR(100),
personId INT NOT NULL,
addressId INT NOT NULL,
FOREIGN KEY `fk_Customer_156_to_Person_156` (personId) REFERENCES Person_156(personId),
FOREIGN KEY `fk_Customer_156_to_Address_156` (addressId) REFERENCES Address_156(AddressId)
);

CREATE TABLE 
IF NOT exists Product_156
(
productId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
productType VARCHAR(1),
ProductDescription varchar(255) NOT NULL
);

CREATE TABLE 
IF NOT exists Concession_156
(
concessionId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
concessionCode VARCHAR(9),
unitCost float NOT NULL,
productId INT NOT NULL,
FOREIGN KEY `fk_Concession_156_to_Product_156` (productId) REFERENCES Product_156(ProductId)
);

CREATE TABLE 
IF NOT exists Rental_156
(
rentalId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
rentalCode VARCHAR(9),
rentalDailyCost float NOT NULL,
rentalDeposit float NOT NULL,
cleaningFee float NOT NULL,
productId INT NOT NULL,
FOREIGN KEY `fk_Rental_156_to_Product_156` (productId) REFERENCES Product_156(ProductId)
);

CREATE TABLE 
IF NOT exists Repair_156
(
repairId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
repairCode VARCHAR(9),
partsRepairCost float NOT NULL,
hourlyRepairCost float NOT NULL,
productId INT NOT NULL,
FOREIGN KEY `fk_Repair_156_to_Product_156` (productId) REFERENCES Product_156(ProductId)
);

CREATE TABLE 
IF NOT exists Tow_156
(
towId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
towCode VARCHAR(9),
towCostPerMile float NOT NULL,
productId INT NOT NULL,
FOREIGN KEY `fk_Tow_156_to_Product_156` (productId) REFERENCES Product_156(ProductId)
);

CREATE TABLE 
IF NOT exists Invoice_156
(
invoiceId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
invoiceCode VARCHAR(10) NOT NULL,
personId INT NOT NULL,
customerId INT NOT NULL,
FOREIGN KEY `fk_Invoice_156_to_Person_156` (personId) REFERENCES Person_156(personId),
FOREIGN KEY `fk_Invoice_156_to_Customer_156` (customerId) REFERENCES Customer_156(customerId)
);

CREATE TABLE 
IF NOT exists InvoiceProduct_156 
(
invoiceProductId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
invoiceId INT NOT NULL,
productId INT NOT NULL,
costMultiplier INT NOT NULL,
associatedRepairCode VARCHAR(10),
FOREIGN KEY `fk_InvoiceProduct_156_to_Invoice_156` (invoiceId) REFERENCES Invoice_156(invoiceId),
FOREIGN KEY `fk_InvoiceProduct_156_to_Product_156` (productId) REFERENCES Product_156(productId)
);

-- Data Entry --
----------------------------------------------------------------------------------------------------

-- Address Entry --

INSERT INTO Address_156(street,city, state, zip, country)
VALUES	
        ('114 Wyatt Street', 'West Palm Beach', 'FL', '33411', 'USA'),
		('3196 Grant Street', 'Plano', 'TX', '75074', 'USA'),
		('3712 Williams Lane', 'Garden Plain', 'KS', '67050', 'USA'),
		('4475 Harper Street', 'Morgantown', 'KY', '42261', 'USA'),
		('458 Ben Street', 'Lansing', 'MI', '48933', 'USA'),
		('12 Harradine Street', 'Brighton', 'England', 'ME99DD', 'England'),
		('2977 Scenic Way', 'Dana', 'IL', '47847', 'USA'),
		('2313 Jenna Lana', 'Cincinnati', 'OH', '45231', 'USA'),
		('3913 Aviation Way', 'Los Angeles', 'CA', '90017', 'USA'),
		('2054 Jones Avenue', 'Yanceyville', 'NC', '27379', 'USA'),
		('423 Bates Brothers Road', 'Westerville', 'OH', '42081', 'USA'),
		('2181 Nicholas Street', 'Junction City', 'KS', '66441', 'USA'),
		('3207 Hog Camp Road', 'La Grange', 'IL', '60525', 'USA'),
		('1126 Ashmor Drive', 'Peoria', 'Il', '61630', 'USA'),
		('4266 Goodwin Avenue', 'Spokane', 'WA', '99201', 'USA'),
        ('779 New Creek Road','Centre','AL','35960','USA'),
		('163 Ocello Street','Moundridge','KS','67107','USA'),
        ('1037 Zimmerman Lane','Los Angeles','CA','90017','USA'),
        ('938 Armory Road','Wilmington','NC','28403','USA'),
        ('2041 Fowler Avenue','Atlanta','GA','30303','USA'),
        ('3043 Forest Drive','Dale City','VA','22193','USA');

-- Person Entry --

INSERT INTO Person_156 (personCode, lastName, firstName, addressId) 
VALUES 	('Kj5ZjMjed', 'Duncan', 'Cecilia', 1),
		('qhyN7G9oT', 'Duncan', 'Gianna', 2),
        ('vacd5nyil', 'Duncan', 'Eva', 3),
		('OQjUGwzBG', 'Feinja', 'Evie', 4),
		('WhSPQUjex', 'Amithlayh', 'Tori', 5),
		('MgBh27YSF', 'Miller', 'Joel', 6),
		('pnEcYx6B8', 'Rubio', 'Juli', 7),
		('NQNxh5hbD', 'McLoughlin', 'Sean', 8),
		('ponWl7Ddz', 'Clark', 'Dodie', 9),
		('VB0H2eeZ1', 'Crowley', 'Beth', 10),
		('BvQDKouUS', 'Ruma', 'Lee', 11),
		('AVJISKbgv', 'Cat', 'Ginger', 12),
		('sfSbXUw5n', 'Hiraide', 'Takashi', 13),
		('sXob6KfTY', 'Gawande', 'Atul', 14),
		('sPlhR4ERC', 'Peppernell', 'Courtney', 15);
        
-- Email Entry --
        
INSERT INTO Email_156 (emailAddress, personId) 
VALUES 	('2mnejja.nourf@tapiitudulu.com', 1),
		('asean.reborn.730@noteg.xyz', 2),
		('jabd@sd110.xyz', 4),
		('0tstanleyallthetj@chrisjoyce.net', 4),
		('3djamel.bidk@bagshare.org', 7),
		('nthifan@mncrafting.com', 9),
		('jabd@sd110.xyz', 10),
		('0tstanleyallthetj@chrisjoyce.net', 10),
		('aswaljmylz@icinki.ml', 11),
		('jriadhbrahmi-201l@knowatef.ga', 12),
		('wabdoup@bokikstore.com', 13),
		('9rider@lebang.site', 14),
		('7best-cmpunkd@walmart-web.com', 14);
        
-- Customer Entry --

INSERT INTO Customer_156(customerCode, customerType, customerName, personId, addressId)
VALUES	('LYC85wdI8','P','Dancing Cars',14,16),
		('9Ghfv1D8Q','P','Musical Cars',10,17),
        ('vOt6Um0CD','B','Car Tech',3,18),
        ('kcpCPabp5','P','Winter Driving',6,19),
        ('hd2T880os','P','TARDIS',8,20),
        ('r23EMj4NO','B','Boise Library',3,21);
        
  -- Product Entry --
  
 INSERT INTO Product_156(productType, ProductDescription)
 VALUES ('R','1967 Chevy Impala'), -- 1
		('F','Windsheild Repair'), -- 2
        ('C','16-oz Coffee'), -- 3
        ('T','Tow > 15 miles'), -- 4
        ('R','2005 Toyota 4 Runner'), -- 5
        ('F','Engine Replacement'), -- 6
        ('C','16-oz Tea'), -- 7
        ('T','Tow < 15 miles'), -- 8
        ('R','2012 Toyota Sienna'), -- 9
        ('F','Oil Change'); -- 10
        
-- Concession Entry --

INSERT INTO Concession_156(concessionCode, unitCost,productId)
VALUES	('Vc5PhEvTK',4.25,3),
		('JgLLovuUT',2.98,7);

-- Rental Entry --

INSERT INTO Rental_156(rentalCode, rentalDailyCost, rentalDeposit, cleaningFee, productId)
VALUES	('4GXqp6LxM',102.75,200,25.55,1),
		('efG1izvXg',86.45,60.24,24.54,5),
        ('QgbJUIxaC',75.56,100.00,20.65,9);
        
-- Repair Entry --

INSERT INTO Repair_156(repairCode, partsRepairCost, hourlyRepairCost, productId)
VALUES	('KzQW71qkj',150.50,30.25,2),
		('wPOObkXsf',1554.23,50.23,6),
        ('ruYJAqcp4',60.50,40.50,10);
 
-- Tow Entry --

INSERT INTO Tow_156(towCode,towCostPerMile, productId)
VALUES	('miga0IebX',6.75,4),
		('dPWRS9BbV',8.50,8);
        
-- Invoice Entry --
 
INSERT INTO Invoice_156(invoiceCode, personId, customerId)
VALUES  ('INV001',8,3),
		('INV002',13,1),
        ('INV003',7,6),
        ('INV004',1,4),
        ('INV005',15,5),
        ('INV006',10,1);
        
INSERT INTO InvoiceProduct_156(invoiceId, productId, costMultiplier)
VALUES  (1,1,66),
		(2,2,8),
        (3,4,30),
        (3,1,2),
        (4,5,21),
        (4,7,1),
        (4,3,2),
        (5,8,12),
        (5,6,15),
        (5,10,100),
        (5,3,2),
        (5,9,5),
        (6,3,2);
        
commit;