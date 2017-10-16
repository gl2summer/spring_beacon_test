DROP TABLE if EXISTS eplates;

CREATE TABLE `eplates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `myMac` varchar(17) NOT NULL,
  `parentMac` varchar(17) NOT NULL,
  `major` int(2) NOT NULL,
  `minor` int(2) NOT NULL,
  `rssiAvr` int(2) NOT NULL,
  `rssiList` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT INTO eplates(myMac,parentMac,major,minor,rssiAvr,rssiList) VALUES("11:22:33:44:55:01","00:00:00:00:00:00",100,201,-10,"-10,-9,-11");
INSERT INTO eplates(myMac,parentMac,major,minor,rssiAvr,rssiList) VALUES("11:22:33:44:55:02","00:00:00:00:00:00",100,202,-10,"-10,-9,-11");
INSERT INTO eplates(myMac,parentMac,major,minor,rssiAvr,rssiList) VALUES("11:22:33:44:55:03","00:00:00:00:00:00",100,203,-10,"-10,-9,-11");
INSERT INTO eplates(myMac,parentMac,major,minor,rssiAvr,rssiList) VALUES("11:22:33:44:55:04","00:00:00:00:00:00",100,204,-10,"-10,-9,-11");
INSERT INTO eplates(myMac,parentMac,major,minor,rssiAvr,rssiList) VALUES('11:22:33:44:55:05','00:00:00:00:00:00',100,204,-10,"-10,-9,-11");


SELECT * FROM eplates;