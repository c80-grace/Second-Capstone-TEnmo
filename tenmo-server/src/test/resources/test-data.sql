BEGIN TRANSACTION;

INSERT INTO tenmo_user (username,password_hash,role) VALUES ('user1','user1','ROLE_USER');
INSERT INTO tenmo_user (username,password_hash,role) VALUES ('user2','user2','ROLE_USER');
INSERT INTO tenmo_user (username,password_hash,role) VALUES ('user3','user3','ROLE_USER');

INSERT INTO tenmo_account (account_id, user_id, balance) VALUES ('2001', '1001', '1000');
INSERT INTO tenmo_account (account_id, user_id, balance) VALUES ('2005', '1002', '1000');
INSERT INTO tenmo_account (account_id, user_id, balance) VALUES ('2009', '1003', '1000');


INSERT INTO tenmo_transfer (transfer_id, transfer_type_id,
transfer_status_id, account_from, account_to, amount)
VALUES (3001, 2, 2, 2001, 2005, 500);
INSERT INTO tenmo_transfer (transfer_id, transfer_type_id,
transfer_status_id, account_from, account_to, amount)
VALUES (3002, 2, 2, 2009, 2001, 500);

COMMIT TRANSACTION;
