delete from bid_list;
delete from curve_point;
delete from rating;
delete from rule;
delete from trade;
delete from user where id=2;
delete from user where id=3;
delete from user where id=4;
insert into bid_list(bidlistid, account, bid_quantity, type) values(1, 'Tests1', 10, 'DescTests1');
insert into bid_list(bidlistid, account, bid_quantity, type) values(2, 'Tests2', 10, 'DescTests2');
insert into curve_point(id, curve_id, term, value) values (1, 1, 2, 3);
insert into curve_point(id, curve_id, term, value) values (2, 4, 5, 6);
insert into rating(id, moodys_rating, sandprating, order_number) values (10, '1', '2', 3);
insert into rating(id, moodys_rating, sandprating, order_number) values (20, '4', '5', 6);
insert into rule(ruleid, name, description, json, template, sql_str, sql_part) values (1, '1', '2', '3', '4', '5', '6');
insert into rule(ruleid, name, description, json, template, sql_str, sql_part) values (2, '7', '8', '9', '10', '11', '12');
insert into trade(trade_id, account, type, buy_quantity) values (1, '1', '2', 3);
insert into trade(trade_id, account, type, buy_quantity) values (2, '4', '5', 6);
insert into user(id, fullname, password, username, role) values (2, 'cyrille', 'Rsv1000R!', 'cyrille', 'USER');
insert into user(id, fullname, password, username, role) values (3, '1', 'Rsv1000R!', '1', 'USER');
insert into user(id, fullname, password, username, role) values (4, '2', 'Rsv1000R!', '2', 'USER');


