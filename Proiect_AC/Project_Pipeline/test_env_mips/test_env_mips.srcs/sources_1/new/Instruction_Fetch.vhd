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
	  B"001_000_110_0000100", 	-- addi $6, $0, 4						--0
	  B"001_000_100_0000001", 	-- addi $4, $0, 1					  	--1
	  B"000_001_000_0000101", 	-- addi $1, $0, 5				  		--2
	  B"100_000_001_0011010",	-- beq $1, $0, etich1    etich1 = 26				--3		
	
	  B"001_000_000_0000000",	-- addi $0, $0, 0					   	--4
	  B"001_000_000_0000000",	-- addi $0, $0, 0					 	--5
	  B"001_000_000_0000000",	-- addi $0, $0, 0					 	--6
													
	  B"000_001_100_010_0_100",	-- and $2, $1, $4				   	  	--7
													
	  B"001_000_000_0000000",	-- addi $0, $0, 0					 	--8
	  B"001_000_000_0000000",	-- addi $0, $0, 0					 	--9
	  B"001_000_000_0000000",	-- addi $0, $0, 0					 	--10

	  B"000_000_010_011_0_000",	-- add $3, $0, $2				   		--11
	  B"000_001_000_001_1_000",	-- srl $1, $1, 1				   	   	--12
	  B"001_000_101_0000001",	-- addi $5, $0, 1					   	--13

	  B"001_000_000_0000000",	-- addi $0, $0, 0					 	--14
	  B"001_000_000_0000000",	-- addi $0, $0, 0					 	--15

	  B"100_000_001_0011001",	-- etich4: beq $1, $0, etich3=25				--16
	  B"000_001_100_010_0_100",	-- and $2, $1, $4				   	  	--17
	  B"000_001_000_001_1_000", 	-- srl $1, $1, 1				   		--18

	  B"001_000_000_0000000",	-- addi $0, $0, 0 NoOP				 		--19
	  B"001_000_000_0000000",	-- addi $0, $0, 0 NoOP				 		--20

	  B"100_010_011_0010111",	-- beq $3, $2, x_egal_y=23			 		--21
	  B"000_101_100_101_0_000",	-- add $5, $5, $4				   	  	--22
	  B"000_000_010_011_0_000",	-- x_egal_y: add, $3, $0, $2		  			--23
	  B"111_0000000010000",		-- j etich4	etich4=16			  		--24

	  B"001_000_000_0000000",	-- etich3: addi $0, $0, 0 NoOP		 			--25

	  B"101_110_101_0100001",	-- etich1: bne $5, $6, inegalit=33				--26

	  B"001_000_000_0000000",	-- addi $0, $0, 0 NoOP				 		--27
	  B"001_000_000_0000000",	-- addi $0, $0, 0 NoOP				 		--28
	  B"001_000_000_0000000",	-- addi $0, $0, 0 NoOP				 		--29

	  B"001_000_101_1000000",	-- addi $5, $0, 64					  	--30
	  B"111_0000000100010",		-- j equale=34					   		--31

	  B"001_000_000_0000000",	-- addi $0, $0, 0 NoOP				 		--32

	  B"001_000_101_0000100",	-- inegalit: addi $5, $0, 4					--33
	  B"001_000_000_0000000",	-- equale: addi $0, $0, 0 NoOP		 			--34

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