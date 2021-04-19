library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Register_File is
  Port (clk:in std_logic;
  	ra1:in std_logic_vector(2 downto 0);
  	ra2:in std_logic_vector(2 downto 0);
  	wa:in std_logic_vector(2 downto 0);
  	wd:in std_logic_vector(15 downto 0);
  	wen:in std_logic;
  	rd1, rd2:out std_logic_vector(15 downto 0));
end Register_File;

architecture Register_File_arh of Register_File is

type rfile is array(0 to 255) of std_logic_vector(15 downto 0);
signal rff: rfile :=(x"0232", x"0234", x"0236", x"0238", x"0240", 
		x"0242", x"0244", x"0246", x"0248", x"0250", x"0252",
		x"0254", x"0256", x"0258", x"0260", others=>x"FFFF");

	begin					  
	process(clk)
		begin
		if rising_edge(clk) then
			if wen='1' then
    			rff(conv_integer(wa) ) <= wd;
			end if;
		end if;
	end process; 
   
rd1 <= rff(conv_integer(ra1) );
rd2 <= rff(conv_integer(ra2) );

end Register_File_arh;