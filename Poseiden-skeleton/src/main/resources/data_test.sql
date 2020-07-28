delete from PoseidonTest2.bid_list;
delete from PoseidonTest2.curve_point;
delete from PoseidonTest2.rating;
delete from PoseidonTest2.rule;
delete from PoseidonTest2.trade;
insert into PoseidonTest2.bid_list(bidlistid, account, bid_quantity, type) values(1, 'Tests1', 10, 'DescTests1');
insert into PoseidonTest2.bid_list(bidlistid, account, bid_quantity, type) values(2, 'Tests2', 10, 'DescTests2');
insert into PoseidonTest2.curve_point(id, curve_id, term, value) values (1, 1, 2, 3);
insert into PoseidonTest2.curve_point(id, curve_id, term, value) values (2, 4, 5, 6);
insert into PoseidonTest2.rating(id, moodys_rating, sandprating, order_number) values (1, '1', '2', 3);
insert into PoseidonTest2.rating(id, moodys_rating, sandprating, order_number) values (2, '4', '5', 6);
insert into PoseidonTest2.rule(id, name, description, json, template, sql_str, sql_part) values (1, '1', '2', '3', '4', '5', '6');
insert into PoseidonTest2.rule(id, name, description, json, template, sql_str, sql_part) values (2, '7', '8', '9', '10', '11', '12');
insert into PoseidonTest2.trade(trade_id, account, type, buy_quantity) values (1, '1', '2', 3);
insert into PoseidonTest2.trade(trade_id, account, type, buy_quantity) values (2, '4', '5', 6);

