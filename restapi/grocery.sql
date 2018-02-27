CREATE DATABSE GROCERY;

DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `usershop_details`;
CREATE TABLE `usershop_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) NOT NULL,
  `User_ID` varchar(20) DEFAULT NULL,
  `Name` varchar(50) NOT NULL,
  `User_Name` varchar(20) NOT NULL,
  `User_Password` varchar(20) NOT NULL,
  `Phn_Number` varchar(20) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Is_Active` tinyint(1) NOT NULL,
  `Shop_Count` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT UC_usershop_details UNIQUE (User_Name,Phn_Number,Email)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
INSERT INTO `grocery`.`usershop_details` ( `Shop_ID`,  `Name`, `User_Name`, `User_Password`, `Phn_Number`, `Email`, `Is_Active`, `Shop_Count`) 
VALUES ( 'Shop_0',  'Sadddam', 'saddam1', '12345678', '7204414827', 'sksddmhosan@gmail.com', '1', '1');

INSERT INTO `grocery`.`usershop_details` ( `Shop_ID`, `User_ID`, `Name`, `User_Name`, `User_Password`, `Phn_Number`, `Email`, `Is_Active`, `Shop_Count`) 
VALUES ( 'Shop_0', 'User_0', 'Synixia', 'syn', '1234', '7204414827', 'syn@gmail.com', '1', '1');

INSERT INTO `grocery`.`usershop_details` ( `Shop_ID`,  `Name`, `User_Name`, `User_Password`, `Phn_Number`, `Email`, `Is_Active`, `Shop_Count`)
VALUES ( 'Shop_1',  'saddam', 'sdm', '12345', '7204414827', 'sdm@gmail.com', '1', '1');


CREATE TABLE `address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) DEFAULT NULL,
  `User_ID` varchar(20) DEFAULT NULL,
  `House_No` varchar(20) DEFAULT NULL,
  `Locality` varchar(50) DEFAULT NULL,
  `Landmark` varchar(50) DEFAULT NULL,
  `PinCode` varchar(20) DEFAULT NULL,
  `Area` varchar(20) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `Address_Active` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT FK_UserAddress FOREIGN KEY (ID)
    REFERENCES usershop_details(ID)
     
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `grocery`.`address` (`ID`, `Shop_ID`,  `House_No`, `Locality`, `Landmark`, `PinCode`, `Area`, `City`, `Address_Active`)
VALUES ( 'Shop_0',  '#23', 'SGR', 'SGR Dental', '560037', 'Bangalore', 'Bangalore', '1');

INSERT INTO `grocery`.`address` (`ID`, `Shop_ID`, `User_ID`, `House_No`, `Locality`, `Landmark`, `PinCode`, `Area`, `City`, `Address_Active`)
VALUES ( 'Shop_0', 'User_0', '#24', 'Sgr', 'SGR Dental', '560037', 'Marathhalli', 'Bangalore', '1');

INSERT INTO `grocery`.`address` (`ID`, `Shop_ID`,  `House_No`, `Locality`, `Landmark`, `PinCode`, `Area`, `City`, `Address_Active`) 
VALUES ( 'Shop_1',  '#25', 'SGR', 'SGR Dental', '560037', 'Marathhalli', 'Bangalore', '1');


DROP TABLE IF EXISTS `productlist`;
CREATE TABLE `productlist` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) NOT NULL,
  `Product_ID` varchar(40) NOT NULL,
  `Product_Name` varchar(40) DEFAULT NULL,
  `Product_Image` longblob,
  `Product_Category` varchar(20) DEFAULT NULL,
  `Product_Type` varchar(20) DEFAULT NULL,
  `Availability` tinyint(1) DEFAULT NULL,
  `Product_Active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT UC_productlist UNIQUE (Product_ID,Product_Name)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `product_prices`;
CREATE TABLE product_prices (
	
	ID INT NOT NULL AUTO_INCREMENT,
	Shop_ID VARCHAR(20)NOT NULL,
    Product_ID VARCHAR(20)NOT NULL,
	Price VARCHAR(400) NOT NULL,
    Qty_Price VARCHAR(400),
    Stock VARCHAR(400),	
	PRIMARY KEY (`ID`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;












