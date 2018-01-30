CREATE DATABSE GROCERY;

CREATE TABLE `usershop_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) DEFAULT NULL,
  `User_ID` varchar(20) DEFAULT NULL,
  `Name` varchar(50) NOT NULL,
  `User_Name` varchar(20) NOT NULL,
  `User_Password` varchar(20) NOT NULL,
  `Phn_Number` varchar(20) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Is_Active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT UC_usershop_details UNIQUE (User_Name,Phn_Number,Email)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;
INSERT INTO `grocery`.`usershop_details` (`ID`, `Shop_ID`, `User_ID`, `Name`, `User_Name`, `User_Password`, `Phn_Number`, `Email`, `Is_Active`) 
VALUES ('1', 'Shop_0', 'User_0', 'Sadddam', 'saddam1', '12345678', '7204414827', 'sksddmhosan@gmail.com', '1');

CREATE TABLE `address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Shop_ID` varchar(20) NOT NULL,
  `User_ID` varchar(20) NOT NULL,
  `House_No` varchar(20) DEFAULT NULL,
  `Locality` varchar(50) DEFAULT NULL,
  `Landmark` varchar(50) DEFAULT NULL,
  `PinCode` varchar(10) DEFAULT NULL,
  `Area` varchar(20) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL,
  `Is_Active` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT FK_UserAddress FOREIGN KEY (ID)
    REFERENCES usershop_details(ID)
     
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

INSERT INTO `grocery`.`address` (`ID`, `Shop_ID`, `User_ID`, `House_No`, `Locality`, `Landmark`, `PinCode`, `Area`, `City`, `Is_Active`)
VALUES ('1', 'Shop_0', 'User_0', '#23', 'SGR', 'SGR Dental', '560037', 'Bangalore', 'Bangalore', '1');









