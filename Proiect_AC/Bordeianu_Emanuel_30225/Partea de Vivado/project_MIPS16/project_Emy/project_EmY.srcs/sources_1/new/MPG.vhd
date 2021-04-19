		  		
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
	   
entity MPG is
    Port ( en : out STD_LOGIC;
           input : in STD_LOGIC;
           clock : in STD_LOGIC);
end MPG;

architecture arh_MPG of MPG is

signal count:std_logic_vector(31 downto 0):=x"00000000";
signal Q1:std_logic;
signal Q2:std_logic;
signal Q3:std_logic;
begin
	en<=Q2 and (not Q3);
	
	process(clock)
		begin
		if rising_edge( clock ) then
			count<=count+1;
		end if;
	end process;
	
	process(clock)
	begin 
		if rising_edge(clock) then
			if count(15 downto 0)="1111111111111111" then
				Q1<=input;
			end if;
		end if;
	end process; 
	
	process(clock)
	begin 
		if clock'event and clock='1' then
			Q2<=Q1;
			Q3<=Q2;
		end if;
	end process;

end arh_MPG;