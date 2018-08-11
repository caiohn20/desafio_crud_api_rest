SET NAMES utf8 ;


LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'caio','caio','caio'),(3,'caio_alt','caio_alt','caio_alt'),(4,'caio_add','caio_add','caio_add'),(5,'caio_add','caio_add','caio_add');

UNLOCK TABLES;
