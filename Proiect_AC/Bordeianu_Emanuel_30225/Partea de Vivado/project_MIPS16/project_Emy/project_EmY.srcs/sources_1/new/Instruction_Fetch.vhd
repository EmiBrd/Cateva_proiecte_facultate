library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Instruction_Fetch is
    Port ( clk : in STD_LOGIC;
           reset : in STD_LOGIC;
           en : in STD_LOGIC;	 
		   jump_adress : in STD_LOGIC_VECTOR (15 downto 0);	
           branch_adress : in STD_LOGIC_VECTOR (15 downto 0);           
		   jump : in STD_LOGIC;
           pcsrc : in STD_LOGIC;
           instruction : out STD_LOGIC_VECTOR (15 downto 0);
           next_instruction : out STD_LOGIC_VECTOR (15 downto 0));
end Instruction_Fetch;

architecture Instruction_Fetch_arh of Instruction_Fetch is

type memorie is array (0 to 255) of std_logic_vector (15 downto 0);

--- Legenda : 
--- $zero => $0
--- $t1 => $1
--- $s1 => $2
--- $s3 => $3
--- $s4 => $4
--- $k0 => $5
--- $k1 => $6

signal memmory: memorie := (
B"001_000_110_0000100", 	-- addi $k1, $0, 4	  							--1
B"001_000_100_0000001", 	-- addi $s4, $0, 1								--2
B"000_001_000_0000101", 	-- addi $t1, $0, 5							   	--3
B"100_000_001_0010001",		-- beq $t1, $0, etich1    etich = 17	   		--4
B"000_001_100_010_0_100",	-- and $s1, $t1, $s4						   	--5
B"000_000_010_011_0_000",	-- add $s3, $0, $s1							   	--6
B"000_001_000_001_1_000",	-- srl $t1, $t1, 1							   	--7
B"001_000_101_0000001",		-- addi $k0, $zero, 1							--8
B"100_000_001_0010000",		-- etich4:	beq $t1, $0, etich3=16			   	--9
B"000_001_100_010_0_100",	-- and $s1, $t1, $s4						   	--10
B"000_001_000_001_1_000", 	-- srl $t1, $t1, 1							   	--11
B"100_010_011_0001110",		-- beq $s3, $s1, x_egal_y=14				 	--12
B"000_101_100_101_0_000",	-- add $k0, $k0, $s4						   	--13
B"000_000_010_011_0_000",	-- x_egal_y: add, $s3, $zero, $s1			   	--14
B"111_0000000001001",		-- j etich4									  	--15
B"101_110_101_0010101",		-- bne $k0, $k1, inegalit=21				  	--18
B"001_000_101_1000000",		-- addi $k0, $zero, 64							--19
B"111_0000000010110",		-- j equale=22								   	--20
B"001_000_101_0000100",		-- inegalit: addi $k0, $zero, 4					--21

others => x"FFFF"  );	

signal pcc:STD_LOGIC_VECTOR(15 downto 0):=x"0000";
signal pcc1:STD_LOGIC_VECTOR(15 downto 0):=x"0000";
signal mm1:STD_LOGIC_VECTOR(15 downto 0):=x"0000";

begin
    process(clk, reset, en)
    begin
    if reset = '1' then 
        pcc <= x"0000";
        elsif en='1' and rising_edge(clk) 
        then pcc <= pcc1;
    end if;
end process;

instruction <= memmory(conv_integer(pcc(7 downto 0) ) );
next_instruction <= pcc+1;
mm1 <= pcc+1 when pcsrc = '0'
	else branch_adress;
pcc1<=mm1 when jump = '0' 
	else jump_adress;

end Instruction_Fetch_arh;