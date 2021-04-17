

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity wallace16 is
Port ( A : in  STD_LOGIC_VECTOR (15 downto 0);
    B : in  STD_LOGIC_VECTOR (15 downto 0);
    prod : out  STD_LOGIC_VECTOR (31 downto 0)
    );
end wallace16;


architecture Behavioral of wallace16 is

component full_adder is
    Port ( a : in  STD_LOGIC;
           b : in  STD_LOGIC;
           c : in  STD_LOGIC;
           sum : out  STD_LOGIC;
           cout : out  STD_LOGIC);
end component;

type tip_pp is array (0 to 15) of std_logic_vector (15 downto 0);

signal pp : tip_pp;

-------- st 1
signal s_101, s_102, s_103, s_104, s_105, s_106, s_107, s_108 : STD_LOGIC := '0';
signal s_109, s_110, s_111, s_112, s_113, s_114, s_115 : STD_LOGIC := '0';

signal cout_101, cout_102, cout_103, cout_104, cout_105, cout_106, cout_107, cout_108 : STD_LOGIC := '0';
signal cout_109, cout_110, cout_111, cout_112, cout_113, cout_114, cout_115 : STD_LOGIC := '0';

-------- st 2
signal s_201, s_202, s_203, s_204, s_205, s_206, s_207, s_208 : STD_LOGIC := '0';
signal s_209, s_210, s_211, s_212, s_213, s_214, s_215 : STD_LOGIC := '0';

signal cout_201, cout_202, cout_203, cout_204, cout_205, cout_206, cout_207, cout_208 : STD_LOGIC := '0';
signal cout_209, cout_210, cout_211, cout_212, cout_213, cout_214, cout_215 : STD_LOGIC := '0';

-------- st 3
signal s_301, s_302, s_303, s_304, s_305, s_306, s_307, s_308 : STD_LOGIC := '0';
signal s_309, s_310, s_311, s_312, s_313, s_314, s_315 : STD_LOGIC := '0';

signal cout_301, cout_302, cout_303, cout_304, cout_305, cout_306, cout_307, cout_308 : STD_LOGIC := '0';
signal cout_309, cout_310, cout_311, cout_312, cout_313, cout_314, cout_315 : STD_LOGIC := '0';

-------- st 4
signal s_401, s_402, s_403, s_404, s_405, s_406, s_407, s_408 : STD_LOGIC := '0';
signal s_409, s_410, s_411, s_412, s_413, s_414, s_415 : STD_LOGIC := '0';

signal cout_401, cout_402, cout_403, cout_404, cout_405, cout_406, cout_407, cout_408 : STD_LOGIC := '0';
signal cout_409, cout_410, cout_411, cout_412, cout_413, cout_414, cout_415 : STD_LOGIC := '0';

-------- st 5
signal s_501, s_502, s_503, s_504, s_505, s_506, s_507, s_508 : STD_LOGIC := '0';
signal s_509, s_510, s_511, s_512, s_513, s_514, s_515 : STD_LOGIC := '0';

signal cout_501, cout_502, cout_503, cout_504, cout_505, cout_506, cout_507, cout_508 : STD_LOGIC := '0';
signal cout_509, cout_510, cout_511, cout_512, cout_513, cout_514, cout_515 : STD_LOGIC := '0';

-------- st 6
signal s_601, s_602, s_603, s_604, s_605, s_606, s_607, s_608 : STD_LOGIC := '0';
signal s_609, s_610, s_611, s_612, s_613, s_614, s_615 : STD_LOGIC := '0';

signal cout_601, cout_602, cout_603, cout_604, cout_605, cout_606, cout_607, cout_608 : STD_LOGIC := '0';
signal cout_609, cout_610, cout_611, cout_612, cout_613, cout_614, cout_615 : STD_LOGIC := '0';

-------- st 7
signal s_701, s_702, s_703, s_704, s_705, s_706, s_707, s_708 : STD_LOGIC := '0';
signal s_709, s_710, s_711, s_712, s_713, s_714, s_715 : STD_LOGIC := '0';

signal cout_701, cout_702, cout_703, cout_704, cout_705, cout_706, cout_707, cout_708 : STD_LOGIC := '0';
signal cout_709, cout_710, cout_711, cout_712, cout_713, cout_714, cout_715 : STD_LOGIC := '0';

-------- st 8
signal s_801, s_802, s_803, s_804, s_805, s_806, s_807, s_808 : STD_LOGIC := '0';
signal s_809, s_810, s_811, s_812, s_813, s_814, s_815 : STD_LOGIC := '0';

signal cout_801, cout_802, cout_803, cout_804, cout_805, cout_806, cout_807, cout_808 : STD_LOGIC := '0';
signal cout_809, cout_810, cout_811, cout_812, cout_813, cout_814, cout_815 : STD_LOGIC := '0';

-------- st 9
signal s_901, s_902, s_903, s_904, s_905, s_906, s_907, s_908 : STD_LOGIC := '0';
signal s_909, s_910, s_911, s_912, s_913, s_914, s_915 : STD_LOGIC := '0';

signal cout_901, cout_902, cout_903, cout_904, cout_905, cout_906, cout_907, cout_908 : STD_LOGIC := '0';
signal cout_909, cout_910, cout_911, cout_912, cout_913, cout_914, cout_915 : STD_LOGIC := '0';

-------- st 10
signal s_1001, s_1002, s_1003, s_1004, s_1005, s_1006, s_1007, s_1008 : STD_LOGIC := '0';
signal s_1009, s_1010, s_1011, s_1012, s_1013, s_1014, s_1015 : STD_LOGIC := '0';

signal cout_1001, cout_1002, cout_1003, cout_1004, cout_1005, cout_1006, cout_1007, cout_1008 : STD_LOGIC := '0';
signal cout_1009, cout_1010, cout_1011, cout_1012, cout_1013, cout_1014, cout_1015 : STD_LOGIC := '0';

-------- st 11
signal s_1101, s_1102, s_1103, s_1104, s_1105, s_1106, s_1107, s_1108 : STD_LOGIC := '0';
signal s_1109, s_1110, s_1111, s_1112, s_1113, s_1114, s_1115 : STD_LOGIC := '0';

signal cout_1101, cout_1102, cout_1103, cout_1104, cout_1105, cout_1106, cout_1107, cout_1108 : STD_LOGIC := '0';
signal cout_1109, cout_1110, cout_1111, cout_1112, cout_1113, cout_1114, cout_1115 : STD_LOGIC := '0';

-------- st 12
signal s_1201, s_1202, s_1203, s_1204, s_1205, s_1206, s_1207, s_1208 : STD_LOGIC := '0';
signal s_1209, s_1210, s_1211, s_1212, s_1213, s_1214, s_1215 : STD_LOGIC := '0';

signal cout_1201, cout_1202, cout_1203, cout_1204, cout_1205, cout_1206, cout_1207, cout_1208 : STD_LOGIC := '0';
signal cout_1209, cout_1210, cout_1211, cout_1212, cout_1213, cout_1214, cout_1215 : STD_LOGIC := '0';

-------- st 13
signal s_1301, s_1302, s_1303, s_1304, s_1305, s_1306, s_1307, s_1308 : STD_LOGIC := '0';
signal s_1309, s_1310, s_1311, s_1312, s_1313, s_1314, s_1315 : STD_LOGIC := '0';

signal cout_1301, cout_1302, cout_1303, cout_1304, cout_1305, cout_1306, cout_1307, cout_1308 : STD_LOGIC := '0';
signal cout_1309, cout_1310, cout_1311, cout_1312, cout_1313, cout_1314, cout_1315 : STD_LOGIC := '0';

-------- st 14
signal s_1401, s_1402, s_1403, s_1404, s_1405, s_1406, s_1407, s_1408 : STD_LOGIC := '0';
signal s_1409, s_1410, s_1411, s_1412, s_1413, s_1414, s_1415 : STD_LOGIC := '0';

signal cout_1401, cout_1402, cout_1403, cout_1404, cout_1405, cout_1406, cout_1407, cout_1408 : STD_LOGIC := '0';
signal cout_1409, cout_1410, cout_1411, cout_1412, cout_1413, cout_1414, cout_1415 : STD_LOGIC := '0';

-------- st 15
signal s_1501, s_1502, s_1503, s_1504, s_1505, s_1506, s_1507, s_1508 : STD_LOGIC := '0';
signal s_1509, s_1510, s_1511, s_1512, s_1513, s_1514, s_1515 : STD_LOGIC := '0';

signal cout_1501, cout_1502, cout_1503, cout_1504, cout_1505, cout_1506, cout_1507, cout_1508 : STD_LOGIC := '0';
signal cout_1509, cout_1510, cout_1511, cout_1512, cout_1513, cout_1514, cout_1515 : STD_LOGIC := '0';

-------- st 16
signal cout_1601, cout_1602, cout_1603, cout_1604, cout_1605, cout_1606, cout_1607, cout_1608 : STD_LOGIC := '0';
signal cout_1609, cout_1610, cout_1611, cout_1612, cout_1613, cout_1614 : STD_LOGIC := '0';


signal p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15 : STD_LOGIC := '0';
signal p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32 : STD_LOGIC := '0';

begin 

gen_pp1: for i in 0 to 15 generate
        gen_pp2: for j in 0 to 15 generate
            pp(i)(j) <= A(j) and B(i);
        end generate;
    end generate;
    	
--------------- stage 1

F1_01: full_adder port map (a=>pp(1)(0),  b=>pp(0)(1),  c=>'0', sum=>s_101, cout=>cout_101 ); 
F1_02: full_adder port map (a=>pp(1)(1),  b=>pp(0)(2),  c=>'0', sum=>s_102, cout=>cout_102 ); 
F1_03: full_adder port map (a=>pp(1)(2),  b=>pp(0)(3),  c=>'0', sum=>s_103, cout=>cout_103 ); 
F1_04: full_adder port map (a=>pp(1)(3),  b=>pp(0)(4),  c=>'0', sum=>s_104, cout=>cout_104 ); 
F1_05: full_adder port map (a=>pp(1)(4),  b=>pp(0)(5),  c=>'0', sum=>s_105, cout=>cout_105 );
F1_06: full_adder port map (a=>pp(1)(5),  b=>pp(0)(6),  c=>'0', sum=>s_106, cout=>cout_106 ); 
F1_07: full_adder port map (a=>pp(1)(6),  b=>pp(0)(7),  c=>'0', sum=>s_107, cout=>cout_107 ); 
F1_08: full_adder port map (a=>pp(1)(7),  b=>pp(0)(8),  c=>'0', sum=>s_108, cout=>cout_108 ); 
F1_09: full_adder port map (a=>pp(1)(8),  b=>pp(0)(9),  c=>'0', sum=>s_109, cout=>cout_109 ); 
F1_10: full_adder port map (a=>pp(1)(9),  b=>pp(0)(10), c=>'0', sum=>s_110, cout=>cout_110 ); 
F1_11: full_adder port map (a=>pp(1)(10), b=>pp(0)(11), c=>'0', sum=>s_111, cout=>cout_111 ); 
F1_12: full_adder port map (a=>pp(1)(11), b=>pp(0)(12), c=>'0', sum=>s_112, cout=>cout_112 ); 
F1_13: full_adder port map (a=>pp(1)(12), b=>pp(0)(13), c=>'0', sum=>s_113, cout=>cout_113 ); 
F1_14: full_adder port map (a=>pp(1)(13), b=>pp(0)(14), c=>'0', sum=>s_114, cout=>cout_114 ); 
F1_15: full_adder port map (a=>pp(1)(14), b=>pp(0)(15), c=>'0', sum=>s_115, cout=>cout_115 ); 

-----------------------------------------------------------------

------------------------ stage 2

F2_01: full_adder port map (a=>pp(2)(0),  b=>s_102, 	c=>cout_101, sum=>s_201, cout=>cout_201 ); 
F2_02: full_adder port map (a=>pp(2)(1),  b=>s_103, 	c=>cout_102, sum=>s_202, cout=>cout_202 ); 
F2_03: full_adder port map (a=>pp(2)(2),  b=>s_104, 	c=>cout_103, sum=>s_203, cout=>cout_203 ); 
F2_04: full_adder port map (a=>pp(2)(3),  b=>s_105, 	c=>cout_104, sum=>s_204, cout=>cout_204 ); 
F2_05: full_adder port map (a=>pp(2)(4),  b=>s_106, 	c=>cout_105, sum=>s_205, cout=>cout_205 );
F2_06: full_adder port map (a=>pp(2)(5),  b=>s_107, 	c=>cout_106, sum=>s_206, cout=>cout_206 ); 
F2_07: full_adder port map (a=>pp(2)(6),  b=>s_108, 	c=>cout_107, sum=>s_207, cout=>cout_207 ); 
F2_08: full_adder port map (a=>pp(2)(7),  b=>s_109, 	c=>cout_108, sum=>s_208, cout=>cout_208 ); 
F2_09: full_adder port map (a=>pp(2)(8),  b=>s_110, 	c=>cout_109, sum=>s_209, cout=>cout_209 ); 
F2_10: full_adder port map (a=>pp(2)(9),  b=>s_111, 	c=>cout_110, sum=>s_210, cout=>cout_210 ); 
F2_11: full_adder port map (a=>pp(2)(10), b=>s_112, 	c=>cout_111, sum=>s_211, cout=>cout_211 ); 
F2_12: full_adder port map (a=>pp(2)(11), b=>s_113, 	c=>cout_112, sum=>s_212, cout=>cout_212 ); 
F2_13: full_adder port map (a=>pp(2)(12), b=>s_114,     c=>cout_113, sum=>s_213, cout=>cout_213 ); 
F2_14: full_adder port map (a=>pp(2)(13), b=>s_115,     c=>cout_114, sum=>s_214, cout=>cout_214 ); 
F2_15: full_adder port map (a=>pp(2)(14), b=>pp(1)(15), c=>cout_115, sum=>s_215, cout=>cout_215 ); 

---------------------------------------------------------

--------------------------- stage 3

--pp(2)(15);

F3_01: full_adder port map (a=>pp(3)(0),  b=>s_202, 	c=>cout_201, sum=>s_301, cout=>cout_301 ); 
F3_02: full_adder port map (a=>pp(3)(1),  b=>s_203, 	c=>cout_202, sum=>s_302, cout=>cout_302 ); 
F3_03: full_adder port map (a=>pp(3)(2),  b=>s_204, 	c=>cout_203, sum=>s_303, cout=>cout_303 ); 
F3_04: full_adder port map (a=>pp(3)(3),  b=>s_205, 	c=>cout_204, sum=>s_304, cout=>cout_304 ); 
F3_05: full_adder port map (a=>pp(3)(4),  b=>s_206, 	c=>cout_205, sum=>s_305, cout=>cout_305 );
F3_06: full_adder port map (a=>pp(3)(5),  b=>s_207, 	c=>cout_206, sum=>s_306, cout=>cout_306 ); 
F3_07: full_adder port map (a=>pp(3)(6),  b=>s_208, 	c=>cout_207, sum=>s_307, cout=>cout_307 ); 
F3_08: full_adder port map (a=>pp(3)(7),  b=>s_209, 	c=>cout_208, sum=>s_308, cout=>cout_308 ); 
F3_09: full_adder port map (a=>pp(3)(8),  b=>s_210, 	c=>cout_209, sum=>s_309, cout=>cout_309 ); 
F3_10: full_adder port map (a=>pp(3)(9),  b=>s_211, 	c=>cout_210, sum=>s_310, cout=>cout_310 ); 
F3_11: full_adder port map (a=>pp(3)(10), b=>s_212, 	c=>cout_211, sum=>s_311, cout=>cout_311 ); 
F3_12: full_adder port map (a=>pp(3)(11), b=>s_213, 	c=>cout_212, sum=>s_312, cout=>cout_312 ); 
F3_13: full_adder port map (a=>pp(3)(12), b=>s_214,     c=>cout_213, sum=>s_313, cout=>cout_313 ); 
F3_14: full_adder port map (a=>pp(3)(13), b=>s_215,     c=>cout_214, sum=>s_314, cout=>cout_314 ); 
F3_15: full_adder port map (a=>pp(3)(14), b=>pp(2)(15), c=>cout_215, sum=>s_315, cout=>cout_315 );

---------------------------------------------------------

--------------------------- stage 4

--pp(3)(15);

F4_01: full_adder port map (a=>pp(4)(0),  b=>s_302, 	c=>cout_301, sum=>s_401, cout=>cout_401 ); 
F4_02: full_adder port map (a=>pp(4)(1),  b=>s_303, 	c=>cout_302, sum=>s_402, cout=>cout_402 ); 
F4_03: full_adder port map (a=>pp(4)(2),  b=>s_304, 	c=>cout_303, sum=>s_403, cout=>cout_403 ); 
F4_04: full_adder port map (a=>pp(4)(3),  b=>s_305, 	c=>cout_304, sum=>s_404, cout=>cout_404 ); 
F4_05: full_adder port map (a=>pp(4)(4),  b=>s_306, 	c=>cout_305, sum=>s_405, cout=>cout_405 );
F4_06: full_adder port map (a=>pp(4)(5),  b=>s_307, 	c=>cout_306, sum=>s_406, cout=>cout_406 ); 
F4_07: full_adder port map (a=>pp(4)(6),  b=>s_308, 	c=>cout_307, sum=>s_407, cout=>cout_407 ); 
F4_08: full_adder port map (a=>pp(4)(7),  b=>s_309, 	c=>cout_308, sum=>s_408, cout=>cout_408 ); 
F4_09: full_adder port map (a=>pp(4)(8),  b=>s_310, 	c=>cout_309, sum=>s_409, cout=>cout_409 ); 
F4_10: full_adder port map (a=>pp(4)(9),  b=>s_311, 	c=>cout_310, sum=>s_410, cout=>cout_410 ); 
F4_11: full_adder port map (a=>pp(4)(10), b=>s_312, 	c=>cout_311, sum=>s_411, cout=>cout_411 ); 
F4_12: full_adder port map (a=>pp(4)(11), b=>s_313, 	c=>cout_312, sum=>s_412, cout=>cout_412 ); 
F4_13: full_adder port map (a=>pp(4)(12), b=>s_314,     c=>cout_313, sum=>s_413, cout=>cout_413 ); 
F4_14: full_adder port map (a=>pp(4)(13), b=>s_315,     c=>cout_314, sum=>s_414, cout=>cout_414 ); 
F4_15: full_adder port map (a=>pp(4)(14), b=>pp(3)(15), c=>cout_315, sum=>s_415, cout=>cout_415 );

---------------------------------------------------------

--------------------------- stage 5

--pp(4)(15);

F5_01: full_adder port map (a=>pp(5)(0),  b=>s_402, 	c=>cout_401, sum=>s_501, cout=>cout_501 ); 
F5_02: full_adder port map (a=>pp(5)(1),  b=>s_403, 	c=>cout_402, sum=>s_502, cout=>cout_502 ); 
F5_03: full_adder port map (a=>pp(5)(2),  b=>s_404, 	c=>cout_403, sum=>s_503, cout=>cout_503 ); 
F5_04: full_adder port map (a=>pp(5)(3),  b=>s_405, 	c=>cout_404, sum=>s_504, cout=>cout_504 ); 
F5_05: full_adder port map (a=>pp(5)(4),  b=>s_406, 	c=>cout_405, sum=>s_505, cout=>cout_505 );
F5_06: full_adder port map (a=>pp(5)(5),  b=>s_407, 	c=>cout_406, sum=>s_506, cout=>cout_506 ); 
F5_07: full_adder port map (a=>pp(5)(6),  b=>s_408, 	c=>cout_407, sum=>s_507, cout=>cout_507 ); 
F5_08: full_adder port map (a=>pp(5)(7),  b=>s_409, 	c=>cout_408, sum=>s_508, cout=>cout_508 ); 
F5_09: full_adder port map (a=>pp(5)(8),  b=>s_410, 	c=>cout_409, sum=>s_509, cout=>cout_509 ); 
F5_10: full_adder port map (a=>pp(5)(9),  b=>s_411, 	c=>cout_410, sum=>s_510, cout=>cout_510 ); 
F5_11: full_adder port map (a=>pp(5)(10), b=>s_412, 	c=>cout_411, sum=>s_511, cout=>cout_511 ); 
F5_12: full_adder port map (a=>pp(5)(11), b=>s_413, 	c=>cout_412, sum=>s_512, cout=>cout_512 ); 
F5_13: full_adder port map (a=>pp(5)(12), b=>s_414,     c=>cout_413, sum=>s_513, cout=>cout_513 ); 
F5_14: full_adder port map (a=>pp(5)(13), b=>s_415,     c=>cout_414, sum=>s_514, cout=>cout_514 ); 
F5_15: full_adder port map (a=>pp(5)(14), b=>pp(4)(15), c=>cout_415, sum=>s_515, cout=>cout_515 );

---------------------------------------------------------

--------------------------- stage 6

--pp(5)(15);

F6_01: full_adder port map (a=>pp(6)(0),  b=>s_502, 	c=>cout_501, sum=>s_601, cout=>cout_601 ); 
F6_02: full_adder port map (a=>pp(6)(1),  b=>s_503, 	c=>cout_502, sum=>s_602, cout=>cout_602 ); 
F6_03: full_adder port map (a=>pp(6)(2),  b=>s_504, 	c=>cout_503, sum=>s_603, cout=>cout_603 ); 
F6_04: full_adder port map (a=>pp(6)(3),  b=>s_505, 	c=>cout_504, sum=>s_604, cout=>cout_604 ); 
F6_05: full_adder port map (a=>pp(6)(4),  b=>s_506, 	c=>cout_505, sum=>s_605, cout=>cout_605 );
F6_06: full_adder port map (a=>pp(6)(5),  b=>s_507, 	c=>cout_506, sum=>s_606, cout=>cout_606 ); 
F6_07: full_adder port map (a=>pp(6)(6),  b=>s_508, 	c=>cout_507, sum=>s_607, cout=>cout_607 ); 
F6_08: full_adder port map (a=>pp(6)(7),  b=>s_509, 	c=>cout_508, sum=>s_608, cout=>cout_608 ); 
F6_09: full_adder port map (a=>pp(6)(8),  b=>s_510, 	c=>cout_509, sum=>s_609, cout=>cout_609 ); 
F6_10: full_adder port map (a=>pp(6)(9),  b=>s_511, 	c=>cout_510, sum=>s_610, cout=>cout_610 ); 
F6_11: full_adder port map (a=>pp(6)(10), b=>s_512, 	c=>cout_511, sum=>s_611, cout=>cout_611 ); 
F6_12: full_adder port map (a=>pp(6)(11), b=>s_513, 	c=>cout_512, sum=>s_612, cout=>cout_612 ); 
F6_13: full_adder port map (a=>pp(6)(12), b=>s_514,     c=>cout_513, sum=>s_613, cout=>cout_613 ); 
F6_14: full_adder port map (a=>pp(6)(13), b=>s_515,     c=>cout_514, sum=>s_614, cout=>cout_614 ); 
F6_15: full_adder port map (a=>pp(6)(14), b=>pp(5)(15), c=>cout_515, sum=>s_615, cout=>cout_615 );

---------------------------------------------------------

--------------------------- stage 7

--pp(6)(15);

F7_01: full_adder port map (a=>pp(7)(0),  b=>s_602, 	c=>cout_601, sum=>s_701, cout=>cout_701 ); 
F7_02: full_adder port map (a=>pp(7)(1),  b=>s_603, 	c=>cout_602, sum=>s_702, cout=>cout_702 ); 
F7_03: full_adder port map (a=>pp(7)(2),  b=>s_604, 	c=>cout_603, sum=>s_703, cout=>cout_703 ); 
F7_04: full_adder port map (a=>pp(7)(3),  b=>s_605, 	c=>cout_604, sum=>s_704, cout=>cout_704 ); 
F7_05: full_adder port map (a=>pp(7)(4),  b=>s_606, 	c=>cout_605, sum=>s_705, cout=>cout_705 );
F7_06: full_adder port map (a=>pp(7)(5),  b=>s_607, 	c=>cout_606, sum=>s_706, cout=>cout_706 ); 
F7_07: full_adder port map (a=>pp(7)(6),  b=>s_608, 	c=>cout_607, sum=>s_707, cout=>cout_707 ); 
F7_08: full_adder port map (a=>pp(7)(7),  b=>s_609, 	c=>cout_608, sum=>s_708, cout=>cout_708 ); 
F7_09: full_adder port map (a=>pp(7)(8),  b=>s_610, 	c=>cout_609, sum=>s_709, cout=>cout_709 ); 
F7_10: full_adder port map (a=>pp(7)(9),  b=>s_611, 	c=>cout_610, sum=>s_710, cout=>cout_710 ); 
F7_11: full_adder port map (a=>pp(7)(10), b=>s_612, 	c=>cout_611, sum=>s_711, cout=>cout_711 ); 
F7_12: full_adder port map (a=>pp(7)(11), b=>s_613, 	c=>cout_612, sum=>s_712, cout=>cout_712 ); 
F7_13: full_adder port map (a=>pp(7)(12), b=>s_614,     c=>cout_613, sum=>s_713, cout=>cout_713 ); 
F7_14: full_adder port map (a=>pp(7)(13), b=>s_615,     c=>cout_614, sum=>s_714, cout=>cout_714 ); 
F7_15: full_adder port map (a=>pp(7)(14), b=>pp(6)(15), c=>cout_615, sum=>s_715, cout=>cout_715 );

---------------------------------------------------------

--------------------------- stage 8

--pp(7)(15);

F8_01: full_adder port map (a=>pp(8)(0),  b=>s_702, 	c=>cout_701, sum=>s_801, cout=>cout_801 ); 
F8_02: full_adder port map (a=>pp(8)(1),  b=>s_703, 	c=>cout_702, sum=>s_802, cout=>cout_802 ); 
F8_03: full_adder port map (a=>pp(8)(2),  b=>s_704, 	c=>cout_703, sum=>s_803, cout=>cout_803 ); 
F8_04: full_adder port map (a=>pp(8)(3),  b=>s_705, 	c=>cout_704, sum=>s_804, cout=>cout_804 ); 
F8_05: full_adder port map (a=>pp(8)(4),  b=>s_706, 	c=>cout_705, sum=>s_805, cout=>cout_805 );
F8_06: full_adder port map (a=>pp(8)(5),  b=>s_707, 	c=>cout_706, sum=>s_806, cout=>cout_806 ); 
F8_07: full_adder port map (a=>pp(8)(6),  b=>s_708, 	c=>cout_707, sum=>s_807, cout=>cout_807 ); 
F8_08: full_adder port map (a=>pp(8)(7),  b=>s_709, 	c=>cout_708, sum=>s_808, cout=>cout_808 ); 
F8_09: full_adder port map (a=>pp(8)(8),  b=>s_710, 	c=>cout_709, sum=>s_809, cout=>cout_809 ); 
F8_10: full_adder port map (a=>pp(8)(9),  b=>s_711, 	c=>cout_710, sum=>s_810, cout=>cout_810 ); 
F8_11: full_adder port map (a=>pp(8)(10), b=>s_712, 	c=>cout_711, sum=>s_811, cout=>cout_811 ); 
F8_12: full_adder port map (a=>pp(8)(11), b=>s_713, 	c=>cout_712, sum=>s_812, cout=>cout_812 ); 
F8_13: full_adder port map (a=>pp(8)(12), b=>s_714,     c=>cout_713, sum=>s_813, cout=>cout_813 ); 
F8_14: full_adder port map (a=>pp(8)(13), b=>s_715,     c=>cout_714, sum=>s_814, cout=>cout_814 ); 
F8_15: full_adder port map (a=>pp(8)(14), b=>pp(7)(15), c=>cout_715, sum=>s_815, cout=>cout_815 );

---------------------------------------------------------

--------------------------- stage 9

--pp(8)(15);

F9_01: full_adder port map (a=>pp(9)(0),  b=>s_802, 	c=>cout_801, sum=>s_901, cout=>cout_901 ); 
F9_02: full_adder port map (a=>pp(9)(1),  b=>s_803, 	c=>cout_802, sum=>s_902, cout=>cout_902 ); 
F9_03: full_adder port map (a=>pp(9)(2),  b=>s_804, 	c=>cout_803, sum=>s_903, cout=>cout_903 ); 
F9_04: full_adder port map (a=>pp(9)(3),  b=>s_805, 	c=>cout_804, sum=>s_904, cout=>cout_904 ); 
F9_05: full_adder port map (a=>pp(9)(4),  b=>s_806, 	c=>cout_805, sum=>s_905, cout=>cout_905 );
F9_06: full_adder port map (a=>pp(9)(5),  b=>s_807, 	c=>cout_806, sum=>s_906, cout=>cout_906 ); 
F9_07: full_adder port map (a=>pp(9)(6),  b=>s_808, 	c=>cout_807, sum=>s_907, cout=>cout_907 ); 
F9_08: full_adder port map (a=>pp(9)(7),  b=>s_809, 	c=>cout_808, sum=>s_908, cout=>cout_908 ); 
F9_09: full_adder port map (a=>pp(9)(8),  b=>s_810, 	c=>cout_809, sum=>s_909, cout=>cout_909 ); 
F9_10: full_adder port map (a=>pp(9)(9),  b=>s_811, 	c=>cout_810, sum=>s_910, cout=>cout_910 ); 
F9_11: full_adder port map (a=>pp(9)(10), b=>s_812, 	c=>cout_811, sum=>s_911, cout=>cout_911 ); 
F9_12: full_adder port map (a=>pp(9)(11), b=>s_813, 	c=>cout_812, sum=>s_912, cout=>cout_912 ); 
F9_13: full_adder port map (a=>pp(9)(12), b=>s_814,     c=>cout_813, sum=>s_913, cout=>cout_913 ); 
F9_14: full_adder port map (a=>pp(9)(13), b=>s_815,     c=>cout_814, sum=>s_914, cout=>cout_914 ); 
F9_15: full_adder port map (a=>pp(9)(14), b=>pp(8)(15), c=>cout_815, sum=>s_915, cout=>cout_915 );

---------------------------------------------------------

--------------------------- stage 10

--pp(9)(15);

F10_01: full_adder port map (a=>pp(10)(0),  b=>s_902, 	  c=>cout_901, sum=>s_1001, cout=>cout_1001 ); 
F10_02: full_adder port map (a=>pp(10)(1),  b=>s_903, 	  c=>cout_902, sum=>s_1002, cout=>cout_1002 ); 
F10_03: full_adder port map (a=>pp(10)(2),  b=>s_904, 	  c=>cout_903, sum=>s_1003, cout=>cout_1003 ); 
F10_04: full_adder port map (a=>pp(10)(3),  b=>s_905, 	  c=>cout_904, sum=>s_1004, cout=>cout_1004 ); 
F10_05: full_adder port map (a=>pp(10)(4),  b=>s_906, 	  c=>cout_905, sum=>s_1005, cout=>cout_1005 );
F10_06: full_adder port map (a=>pp(10)(5),  b=>s_907, 	  c=>cout_906, sum=>s_1006, cout=>cout_1006 ); 
F10_07: full_adder port map (a=>pp(10)(6),  b=>s_908, 	  c=>cout_907, sum=>s_1007, cout=>cout_1007 ); 
F10_08: full_adder port map (a=>pp(10)(7),  b=>s_909, 	  c=>cout_908, sum=>s_1008, cout=>cout_1008 ); 
F10_09: full_adder port map (a=>pp(10)(8),  b=>s_910, 	  c=>cout_909, sum=>s_1009, cout=>cout_1009 ); 
F10_10: full_adder port map (a=>pp(10)(9),  b=>s_911, 	  c=>cout_910, sum=>s_1010, cout=>cout_1010 ); 
F10_11: full_adder port map (a=>pp(10)(10), b=>s_912, 	  c=>cout_911, sum=>s_1011, cout=>cout_1011 ); 
F10_12: full_adder port map (a=>pp(10)(11), b=>s_913, 	  c=>cout_912, sum=>s_1012, cout=>cout_1012 ); 
F10_13: full_adder port map (a=>pp(10)(12), b=>s_914,     c=>cout_913, sum=>s_1013, cout=>cout_1013 ); 
F10_14: full_adder port map (a=>pp(10)(13), b=>s_915,     c=>cout_914, sum=>s_1014, cout=>cout_1014 ); 
F10_15: full_adder port map (a=>pp(10)(14), b=>pp(9)(15), c=>cout_915, sum=>s_1015, cout=>cout_1015 );

---------------------------------------------------------

--------------------------- stage 11

--pp(10)(15);

F11_01: full_adder port map (a=>pp(11)(0),  b=>s_1002, 	   c=>cout_1001, sum=>s_1101, cout=>cout_1101 ); 
F11_02: full_adder port map (a=>pp(11)(1),  b=>s_1003, 	   c=>cout_1002, sum=>s_1102, cout=>cout_1102 ); 
F11_03: full_adder port map (a=>pp(11)(2),  b=>s_1004, 	   c=>cout_1003, sum=>s_1103, cout=>cout_1103 ); 
F11_04: full_adder port map (a=>pp(11)(3),  b=>s_1005, 	   c=>cout_1004, sum=>s_1104, cout=>cout_1104 ); 
F11_05: full_adder port map (a=>pp(11)(4),  b=>s_1006, 	   c=>cout_1005, sum=>s_1105, cout=>cout_1105 );
F11_06: full_adder port map (a=>pp(11)(5),  b=>s_1007, 	   c=>cout_1006, sum=>s_1106, cout=>cout_1106 ); 
F11_07: full_adder port map (a=>pp(11)(6),  b=>s_1008, 	   c=>cout_1007, sum=>s_1107, cout=>cout_1107 ); 
F11_08: full_adder port map (a=>pp(11)(7),  b=>s_1009, 	   c=>cout_1008, sum=>s_1108, cout=>cout_1108 ); 
F11_09: full_adder port map (a=>pp(11)(8),  b=>s_1010, 	   c=>cout_1009, sum=>s_1109, cout=>cout_1109 ); 
F11_10: full_adder port map (a=>pp(11)(9),  b=>s_1011, 	   c=>cout_1010, sum=>s_1110, cout=>cout_1110 ); 
F11_11: full_adder port map (a=>pp(11)(10), b=>s_1012, 	   c=>cout_1011, sum=>s_1111, cout=>cout_1111 ); 
F11_12: full_adder port map (a=>pp(11)(11), b=>s_1013, 	   c=>cout_1012, sum=>s_1112, cout=>cout_1112 ); 
F11_13: full_adder port map (a=>pp(11)(12), b=>s_1014,     c=>cout_1013, sum=>s_1113, cout=>cout_1113 ); 
F11_14: full_adder port map (a=>pp(11)(13), b=>s_1015,     c=>cout_1014, sum=>s_1114, cout=>cout_1114 ); 
F11_15: full_adder port map (a=>pp(11)(14), b=>pp(10)(15), c=>cout_1015, sum=>s_1115, cout=>cout_1115 );

---------------------------------------------------------

--------------------------- stage 12

--pp(11)(15);

F12_01: full_adder port map (a=>pp(12)(0),  b=>s_1102, 	   c=>cout_1101, sum=>s_1201, cout=>cout_1201 ); 
F12_02: full_adder port map (a=>pp(12)(1),  b=>s_1103, 	   c=>cout_1102, sum=>s_1202, cout=>cout_1202 ); 
F12_03: full_adder port map (a=>pp(12)(2),  b=>s_1104, 	   c=>cout_1103, sum=>s_1203, cout=>cout_1203 ); 
F12_04: full_adder port map (a=>pp(12)(3),  b=>s_1105, 	   c=>cout_1104, sum=>s_1204, cout=>cout_1204 ); 
F12_05: full_adder port map (a=>pp(12)(4),  b=>s_1106, 	   c=>cout_1105, sum=>s_1205, cout=>cout_1205 );
F12_06: full_adder port map (a=>pp(12)(5),  b=>s_1107, 	   c=>cout_1106, sum=>s_1206, cout=>cout_1206 ); 
F12_07: full_adder port map (a=>pp(12)(6),  b=>s_1108, 	   c=>cout_1107, sum=>s_1207, cout=>cout_1207 ); 
F12_08: full_adder port map (a=>pp(12)(7),  b=>s_1109, 	   c=>cout_1108, sum=>s_1208, cout=>cout_1208 ); 
F12_09: full_adder port map (a=>pp(12)(8),  b=>s_1110, 	   c=>cout_1109, sum=>s_1209, cout=>cout_1209 ); 
F12_10: full_adder port map (a=>pp(12)(9),  b=>s_1111, 	   c=>cout_1110, sum=>s_1210, cout=>cout_1210 ); 
F12_11: full_adder port map (a=>pp(12)(10), b=>s_1112, 	   c=>cout_1111, sum=>s_1211, cout=>cout_1211 ); 
F12_12: full_adder port map (a=>pp(12)(11), b=>s_1113, 	   c=>cout_1112, sum=>s_1212, cout=>cout_1212 ); 
F12_13: full_adder port map (a=>pp(12)(12), b=>s_1114,     c=>cout_1113, sum=>s_1213, cout=>cout_1213 ); 
F12_14: full_adder port map (a=>pp(12)(13), b=>s_1115,     c=>cout_1114, sum=>s_1214, cout=>cout_1214 ); 
F12_15: full_adder port map (a=>pp(12)(14), b=>pp(11)(15), c=>cout_1115, sum=>s_1215, cout=>cout_1215 );

---------------------------------------------------------

--------------------------- stage 13

--pp(12)(15);

F13_01: full_adder port map (a=>pp(13)(0),  b=>s_1202, 	   c=>cout_1201, sum=>s_1301, cout=>cout_1301 ); 
F13_02: full_adder port map (a=>pp(13)(1),  b=>s_1203, 	   c=>cout_1202, sum=>s_1302, cout=>cout_1302 ); 
F13_03: full_adder port map (a=>pp(13)(2),  b=>s_1204, 	   c=>cout_1203, sum=>s_1303, cout=>cout_1303 ); 
F13_04: full_adder port map (a=>pp(13)(3),  b=>s_1205, 	   c=>cout_1204, sum=>s_1304, cout=>cout_1304 ); 
F13_05: full_adder port map (a=>pp(13)(4),  b=>s_1206, 	   c=>cout_1205, sum=>s_1305, cout=>cout_1305 );
F13_06: full_adder port map (a=>pp(13)(5),  b=>s_1207, 	   c=>cout_1206, sum=>s_1306, cout=>cout_1306 ); 
F13_07: full_adder port map (a=>pp(13)(6),  b=>s_1208, 	   c=>cout_1207, sum=>s_1307, cout=>cout_1307 ); 
F13_08: full_adder port map (a=>pp(13)(7),  b=>s_1209, 	   c=>cout_1208, sum=>s_1308, cout=>cout_1308 ); 
F13_09: full_adder port map (a=>pp(13)(8),  b=>s_1210, 	   c=>cout_1209, sum=>s_1309, cout=>cout_1309 ); 
F13_10: full_adder port map (a=>pp(13)(9),  b=>s_1211, 	   c=>cout_1210, sum=>s_1310, cout=>cout_1310 ); 
F13_11: full_adder port map (a=>pp(13)(10), b=>s_1212, 	   c=>cout_1211, sum=>s_1311, cout=>cout_1311 ); 
F13_12: full_adder port map (a=>pp(13)(11), b=>s_1213, 	   c=>cout_1212, sum=>s_1312, cout=>cout_1312 ); 
F13_13: full_adder port map (a=>pp(13)(12), b=>s_1214,     c=>cout_1213, sum=>s_1313, cout=>cout_1313 ); 
F13_14: full_adder port map (a=>pp(13)(13), b=>s_1215,     c=>cout_1214, sum=>s_1314, cout=>cout_1314 ); 
F13_15: full_adder port map (a=>pp(13)(14), b=>pp(12)(15), c=>cout_1215, sum=>s_1315, cout=>cout_1315 );

---------------------------------------------------------

--------------------------- stage 14

--pp(13)(15);

F14_01: full_adder port map (a=>pp(14)(0),  b=>s_1302, 	   c=>cout_1301, sum=>s_1401, cout=>cout_1401 ); 
F14_02: full_adder port map (a=>pp(14)(1),  b=>s_1303, 	   c=>cout_1302, sum=>s_1402, cout=>cout_1402 ); 
F14_03: full_adder port map (a=>pp(14)(2),  b=>s_1304, 	   c=>cout_1303, sum=>s_1403, cout=>cout_1403 ); 
F14_04: full_adder port map (a=>pp(14)(3),  b=>s_1305, 	   c=>cout_1304, sum=>s_1404, cout=>cout_1404 ); 
F14_05: full_adder port map (a=>pp(14)(4),  b=>s_1306, 	   c=>cout_1305, sum=>s_1405, cout=>cout_1405 );
F14_06: full_adder port map (a=>pp(14)(5),  b=>s_1307, 	   c=>cout_1306, sum=>s_1406, cout=>cout_1406 ); 
F14_07: full_adder port map (a=>pp(14)(6),  b=>s_1308, 	   c=>cout_1307, sum=>s_1407, cout=>cout_1407 ); 
F14_08: full_adder port map (a=>pp(14)(7),  b=>s_1309, 	   c=>cout_1308, sum=>s_1408, cout=>cout_1408 ); 
F14_09: full_adder port map (a=>pp(14)(8),  b=>s_1310, 	   c=>cout_1309, sum=>s_1409, cout=>cout_1409 ); 
F14_10: full_adder port map (a=>pp(14)(9),  b=>s_1311, 	   c=>cout_1310, sum=>s_1410, cout=>cout_1410 ); 
F14_11: full_adder port map (a=>pp(14)(10), b=>s_1312, 	   c=>cout_1311, sum=>s_1411, cout=>cout_1411 ); 
F14_12: full_adder port map (a=>pp(14)(11), b=>s_1313, 	   c=>cout_1312, sum=>s_1412, cout=>cout_1412 ); 
F14_13: full_adder port map (a=>pp(14)(12), b=>s_1314,     c=>cout_1313, sum=>s_1413, cout=>cout_1413 ); 
F14_14: full_adder port map (a=>pp(14)(13), b=>s_1315,     c=>cout_1314, sum=>s_1414, cout=>cout_1414 ); 
F14_15: full_adder port map (a=>pp(14)(14), b=>pp(13)(15), c=>cout_1315, sum=>s_1415, cout=>cout_1415 );

---------------------------------------------------------

--------------------------- stage 15

--pp(14)(15);

F15_01: full_adder port map (a=>pp(15)(0),  b=>s_1402, 	   c=>cout_1401, sum=>s_1501, cout=>cout_1501 ); 
F15_02: full_adder port map (a=>pp(15)(1),  b=>s_1403, 	   c=>cout_1402, sum=>s_1502, cout=>cout_1502 ); 
F15_03: full_adder port map (a=>pp(15)(2),  b=>s_1404, 	   c=>cout_1403, sum=>s_1503, cout=>cout_1503 ); 
F15_04: full_adder port map (a=>pp(15)(3),  b=>s_1405, 	   c=>cout_1404, sum=>s_1504, cout=>cout_1504 ); 
F15_05: full_adder port map (a=>pp(15)(4),  b=>s_1406, 	   c=>cout_1405, sum=>s_1505, cout=>cout_1505 );
F15_06: full_adder port map (a=>pp(15)(5),  b=>s_1407, 	   c=>cout_1406, sum=>s_1506, cout=>cout_1506 ); 
F15_07: full_adder port map (a=>pp(15)(6),  b=>s_1408, 	   c=>cout_1407, sum=>s_1507, cout=>cout_1507 ); 
F15_08: full_adder port map (a=>pp(15)(7),  b=>s_1409, 	   c=>cout_1408, sum=>s_1508, cout=>cout_1508 ); 
F15_09: full_adder port map (a=>pp(15)(8),  b=>s_1410, 	   c=>cout_1409, sum=>s_1509, cout=>cout_1509 ); 
F15_10: full_adder port map (a=>pp(15)(9),  b=>s_1411, 	   c=>cout_1410, sum=>s_1510, cout=>cout_1510 ); 
F15_11: full_adder port map (a=>pp(15)(10), b=>s_1412, 	   c=>cout_1411, sum=>s_1511, cout=>cout_1511 ); 
F15_12: full_adder port map (a=>pp(15)(11), b=>s_1413, 	   c=>cout_1412, sum=>s_1512, cout=>cout_1512 ); 
F15_13: full_adder port map (a=>pp(15)(12), b=>s_1414,     c=>cout_1413, sum=>s_1513, cout=>cout_1513 ); 
F15_14: full_adder port map (a=>pp(15)(13), b=>s_1415,     c=>cout_1414, sum=>s_1514, cout=>cout_1514 ); 
F15_15: full_adder port map (a=>pp(15)(14), b=>pp(14)(15), c=>cout_1415, sum=>s_1515, cout=>cout_1515 );

---------------------------------------------------------

--------------------------- stage 16

--pp(15)(15);

F16_01: full_adder port map (a=>s_1502,  b=>cout_1501, 	   c=>'0',       sum=>p16, cout=>cout_1601 ); 
F16_02: full_adder port map (a=>s_1503,  b=>cout_1502, 	   c=>cout_1601, sum=>p17, cout=>cout_1602 ); 
F16_03: full_adder port map (a=>s_1504,  b=>cout_1503, 	   c=>cout_1602, sum=>p18, cout=>cout_1603 ); 
F16_04: full_adder port map (a=>s_1505,  b=>cout_1504, 	   c=>cout_1603, sum=>p19, cout=>cout_1604 ); 
F16_05: full_adder port map (a=>s_1506,  b=>cout_1505, 	   c=>cout_1604, sum=>p20, cout=>cout_1605 );
F16_06: full_adder port map (a=>s_1507,  b=>cout_1506, 	   c=>cout_1605, sum=>p21, cout=>cout_1606 ); 
F16_07: full_adder port map (a=>s_1508,  b=>cout_1507, 	   c=>cout_1606, sum=>p22, cout=>cout_1607 ); 
F16_08: full_adder port map (a=>s_1509,  b=>cout_1508, 	   c=>cout_1607, sum=>p23, cout=>cout_1608 ); 
F16_09: full_adder port map (a=>s_1510,  b=>cout_1509, 	   c=>cout_1608, sum=>p24, cout=>cout_1609 ); 
F16_10: full_adder port map (a=>s_1511,  b=>cout_1510, 	   c=>cout_1609, sum=>p25, cout=>cout_1610 ); 
F16_11: full_adder port map (a=>s_1512, b=>cout_1511, 	   c=>cout_1610, sum=>p26, cout=>cout_1611 ); 
F16_12: full_adder port map (a=>s_1513, b=>cout_1512, 	   c=>cout_1611, sum=>p27, cout=>cout_1612 ); 
F16_13: full_adder port map (a=>s_1514, b=>cout_1513,      c=>cout_1612, sum=>p28, cout=>cout_1613 ); 
F16_14: full_adder port map (a=>s_1515, b=>cout_1514,      c=>cout_1613, sum=>p29, cout=>cout_1614 ); 
F16_15: full_adder port map (a=>pp(15)(15), b=>cout_1515,  c=>cout_1614, sum=>p30, cout=>p31 );


p0  <= pp(0)(0);
p1  <= s_101 ;
p2  <= s_201 ;
p3  <= s_301 ;
p4  <= s_401 ;
p5  <= s_501 ;
p6  <= s_601 ;
p7  <= s_701 ;
p8  <= s_801 ;
p9  <= s_901 ;
p10 <= s_1001;
p11 <= s_1101;
p12 <= s_1201;
p13 <= s_1301;
p14 <= s_1401;
p15 <= s_1501;


prod(0)  <= pp(0)(0);
prod(1)  <= s_101 ;
prod(2)  <= s_201 ;
prod(3)  <= s_301 ;
prod(4)  <= s_401 ;
prod(5)  <= s_501 ;
prod(6)  <= s_601 ;
prod(7)  <= s_701 ;
prod(8)  <= s_801 ;
prod(9)  <= s_901 ;
prod(10) <= s_1001;
prod(11) <= s_1101;
prod(12) <= s_1201;
prod(13) <= s_1301;
prod(14) <= s_1401;
prod(15) <= s_1501; 
prod(16) <= p16; 
prod(17) <= p17;
prod(18) <= p18;
prod(19) <= p19;
prod(20) <= p20;
prod(21) <= p21;
prod(22) <= p22;
prod(23) <= p23;
prod(24) <= p24; 
prod(25) <= p25;
prod(26) <= p26;
prod(27) <= p27;
prod(28) <= p28;
prod(29) <= p29;
prod(30) <= p30;
prod(31) <= p31;


end Behavioral;
