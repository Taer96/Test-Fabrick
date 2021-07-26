CREATE TABLE `d_account` (
  `account_id` int NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `d_transaction` (
  `transaction_id` double NOT NULL,
  `operation_id` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `amount` double NOT NULL,
  `currency` varchar(45) NOT NULL,
  `accounting_date` date NOT NULL,
  `value_date` date NOT NULL,
  `fk_account` int NOT NULL,
  PRIMARY KEY (`transaction_id`),
  UNIQUE KEY `transactionId_UNIQUE` (`transaction_id`),
  KEY `d_transaction_account_fk` (`fk_account`),
  CONSTRAINT `d_transaction_account_fk` FOREIGN KEY (`fk_account`) REFERENCES `d_account` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;