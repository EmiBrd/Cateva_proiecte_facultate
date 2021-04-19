library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Instruction_Decode is
	Port ( clk: in std_logic;	  
			Write_Data: in std_logic_vector(15 downto 0);
			Write_A:in std_logic_vector(2 downto 0);
			Instruction: in std_logic_vector(15 downto 0);			
			Ext_Op: in std_logic;  
			Reg_Dst: in std_logic;	  
			Reg_Write: in std_logic;		 
			Ext_Imm : out std_logic_vector(15 downto 0);
			Read_Data_1: out std_logic_vector(15 downto 0);
			Read_Data_2: out std_logic_vector(15 downto 0);
			SA : out std_logic;
			Functionn : out std_logic_vector(2 downto 0) );		
end Instruction_Decode;

architecture Instruction_Decode_arh of Instruction_Decode is

component Register_File is
     Port (clk:in std_logic;
  		ra1:in std_logic_vector(2 downto 0);
  		ra2:in std_logic_vector(2 downto 0);
  		wa:in std_logic_vector(2 downto 0);
  		wd:in std_logic_vector(15 downto 0);
  		wen:in std_logic;
  		rd1, rd2:out std_logic_vector(15 downto 0));
end component;

signal RA1_semnal, RA2_semnal: std_logic_vector(2 downto 0); 
signal ExtImm_semnal: std_logic_vector(15 downto 0);
signal WA_semnal: std_logic_vector(2 downto 0) := "000";

begin


----- Sign Extension
process( Ext_Op, Instruction )   
begin
	if (Ext_Op = '0') then
		ExtImm_semnal <= B"000000000" & Instruction(6 downto 0);
		
	else 
		if (Instruction(6) = '0') then
			ExtImm_semnal <= B"000000000" & Instruction(6 downto 0);
		elsif( Instruction(6) = '1' ) then	
			ExtImm_semnal <= B"111111111" & Instruction(6 downto 0);
		end if;
	end if;
end process;


RA1_semnal <= Instruction(12 downto 10);		
RA2_semnal <= Instruction(9 downto 7);

Functionn <= Instruction(2 downto 0);	-- Function ALU
SA <= Instruction(3);		-- SA
Ext_Imm <= ExtImm_semnal;		-- Signal Ext

														
RF: Register_File port map (clk, RA1_semnal, RA2_semnal,Write_A, Write_Data, 
					Reg_Write, Read_Data_1, Read_Data_2);

end Instruction_Decode_arh;