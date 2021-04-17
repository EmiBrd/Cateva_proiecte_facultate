

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity full_adder is
port ( a : in std_logic;
    b: in std_logic ;
    c : in std_logic ;
    sum : out std_logic ; 
    cout : out std_logic
    );
end full_adder;


architecture Behavioral of full_adder is

begin

    sum <= (a xor b xor c);
    cout <= (a and b) or (c and b) or (a and c);


end Behavioral;
