library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
	   
entity Memorie_memory is
	
	port(
 clk: in std_logic; 
		Mem_Write: in std_logic;
		ALURes : in std_logic_vector(15 downto 0);
		Write_Data: in std_logic_vector(15 downto 0);							
		Read_Data:out std_logic_vector(15 downto 0);
			
		ALURes2 :out std_logic_vector(15 downto 0)
	);

end Memorie_memory;




architecture Memorie_memory_arh of Memorie_memory is
  

signal Address: std_logic_vector(3 downto 0);

type ram_type is array (0 to 15) of std_logic_vector(15 downto 0);

signal RAM:ram_type:= ( X"0004", X"0005", X"0006", X"0007", 
	X"0008", X"0009", X"000A", X"000B", others => X"FFFF" );
		


begin

	Address <= ALURes(3 downto 0);

	process(clk) 			

	begin
	
		if(rising_edge(clk)) then	
			if Mem_Write='1' then
		
				RAM(conv_integer(Address))<= Write_Data;	
			
end if;	
		
end if;
		Read_Data <= RAM(conv_integer(Address) );
	
	
end process;

	
ALURes2 <= ALURes;		



end Memorie_memory_arh;
