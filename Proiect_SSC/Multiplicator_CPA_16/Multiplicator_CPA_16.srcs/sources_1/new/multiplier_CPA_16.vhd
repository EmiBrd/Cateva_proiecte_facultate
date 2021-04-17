

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;


entity multiplier_CPA_16 is
port ( a : in std_logic_vector(15 downto 0);
    b : in std_logic_vector(15 downto 0);
    prod: out std_logic_vector(31 downto 0)
    );
end multiplier_CPA_16;


architecture Behavioral of multiplier_CPA_16 is

component carry_prop_adder is
port ( x : in std_logic_vector(31 downto 0);
    y : in std_logic_vector(31 downto 0);
    sum: out std_logic_vector(31 downto 0)
    );
end component;

type sr is array (0 to 15) of std_logic_vector(31 downto 0);
signal r : sr;
signal dummy_prod : sr;

begin

  r(0) <= "0000000000000000" & a when b(0) = '1' else "00000000000000000000000000000000";
  r(1) <= "000000000000000" & a & "0" when b(1) = '1' else "00000000000000000000000000000000";
  r(2) <= "00000000000000" & a & "00" when b(2) = '1' else "00000000000000000000000000000000";
  r(3) <= "0000000000000" & a & "000"  when b(3) = '1' else "00000000000000000000000000000000";
  r(4) <= "000000000000" & a & "0000"  when b(4) = '1' else "00000000000000000000000000000000";
  r(5) <= "00000000000" & a & "00000"  when b(5) = '1' else "00000000000000000000000000000000";
  r(6) <= "0000000000" & a & "000000"  when b(6) = '1' else "00000000000000000000000000000000";
  r(7) <= "000000000" & a & "0000000"  when b(7) = '1' else "00000000000000000000000000000000"; 	  
  r(8) <= "00000000" & a & "00000000"  when b(8) = '1' else "00000000000000000000000000000000";
  r(9) <= "0000000"	& a & "000000000"  when b(9) = '1' else "00000000000000000000000000000000";
  r(10) <= "000000"	& a & "0000000000" when b(10) = '1' else "00000000000000000000000000000000";
  r(11) <= "00000" & a & "00000000000" when b(11) = '1' else "00000000000000000000000000000000";
  r(12) <= "0000" & a & "000000000000" when b(12) = '1' else "00000000000000000000000000000000";
  r(13) <= "000" & a & "0000000000000" when b(13) = '1' else "00000000000000000000000000000000";
  r(14) <= "00"	& a & "00000000000000" when b(14) = '1' else "00000000000000000000000000000000";
  r(15) <= "0" & a & "000000000000000" when b(15) = '1' else "00000000000000000000000000000000";
	  
	  
INITIALIZ : carry_prop_adder port map(x=>r(0),y=>r(1),sum=>dummy_prod(1) );

MULTIPLY : for i in 2 to 15 generate
        MTP: carry_prop_adder port map(x=>dummy_prod(i-1), y=>r(i), sum=>dummy_prod(i) );
    end generate;
    
    prod <= dummy_prod(15);


end Behavioral;
