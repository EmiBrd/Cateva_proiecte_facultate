

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity full_adder is
port ( a : in std_logic;
    b: in std_logic ;
    cin : in std_logic ;
    sum : out std_logic ; 
    cout : out std_logic
    );
end full_adder;


architecture Behavioral of full_adder is

begin

    sum <= (a xor b xor cin);
    cout <= (a and b) or (cin and b) or (a and cin);


end Behavioral;
