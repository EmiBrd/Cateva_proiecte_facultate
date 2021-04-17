

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity carry_prop_adder is
port ( x: in std_logic_vector(31 downto 0);
    y :in std_logic_vector(31 downto 0);
    sum: out std_logic_vector(31 downto 0)
    );
end carry_prop_adder;


architecture Behavioral of carry_prop_adder is

component full_adder is
port ( a : in std_logic;
    b: in std_logic ;
    cin : in std_logic ;
    sum : out std_logic ; 
    cout : out std_logic
    );
end component;

signal cout_cin : std_logic_vector (31 downto 1);

begin

    sum(0) <= x(0);

FA1 : full_adder port map( a=>x(1),b=>y(1), cin=>'0', sum=>sum(1),cout=>cout_cin(1) ); 
    
FA2_7 : for i in 2 to 31 generate                  
           FA : full_adder port map(a=>x(i),b=>y(i),cin=>cout_cin(i-1),
                sum=>sum(i),cout=>cout_cin(i));
          end generate;
          

end Behavioral;
