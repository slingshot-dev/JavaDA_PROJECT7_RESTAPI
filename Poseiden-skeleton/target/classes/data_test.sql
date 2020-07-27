delete from PoseidonTest2.bid_list;
delete from PoseidonTest2.curve_point;
insert into PoseidonTest2.bid_list(bidlistid, account, bid_quantity, type) values(1, 'Tests1', 10, 'DescTests1');
insert into PoseidonTest2.bid_list(bidlistid, account, bid_quantity, type) values(2, 'Tests2', 10, 'DescTests2');
insert into PoseidonTest2.curve_point(id, curve_id, term, value) values (1, 1, 2, 3);
insert into PoseidonTest2.curve_point(id, curve_id, term, value) values (2, 4, 5, 6);