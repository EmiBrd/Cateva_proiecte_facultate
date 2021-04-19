library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Unit_Control is
		Port (Instr: in std_logic_vector(2 downto 0);
			 Reg_Dst: out std_logic;
			 Ext_Op: out std_logic;
			 ALU_Src: out std_logic;
			 Branch: out std_logic;		   
			 bne : out std_logic;
			 Jump: out std_logic;
			 ALU_Op: out std_logic_vector(2 downto 0);
			 Mem_Write: out std_logic;
			 Mem_to_Reg: out std_logic;
			 Reg_Write: out std_logic);
end Unit_Control;


architecture Unit_Control_arh of Unit_Control is
begin
process(Instr)
begin
	case (Instr) is 
		when "000" =>    --- add(+) de tip R
			Reg_Dst <= '1';
			Ext_Op <= 'X';
			ALU_Src <= '0';
			Branch <= '0';
			Jump <= '0';
			ALU_Op <= "000";
			Mem_Write <= '0';
			Mem_to_Reg <= '0';
			Reg_Write <= '1';
			
		when "001" =>      -- sub(-) de tip R
			Reg_Dst <= '0';
			Ext_Op <= 'X';
			ALU_Src <= '0';
			Branch<='0';
			Jump<='0';
			ALU_Op<="100";
			Mem_Write<='0';
			Mem_to_Reg<='0';
			Reg_Write<='1';
			
		when "010"=> --- or de tip R
			Reg_Dst <= '1';
			Ext_Op <= 'X';
			ALU_Src <= '0';
			Branch <= '0';
			Jump <= '0';
			ALU_Op <= "010";
			Mem_Write <= '0';
			Mem_to_Reg <= '0';
			Reg_Write <= '1';
			
		when "011"=> --- and de tip R
			Reg_Dst <= '1';
			Ext_Op <= 'X';
			ALU_Src <= '0';
			Branch <= '0';
			Jump <= '0';
			ALU_Op <= "001";
			Mem_Write <= '0';
			Mem_to_Reg <= '0';
			Reg_Write <= '1';
			
		when "100"=> ---- ori
			Reg_Dst <= '0';
			Ext_Op <= '0';
			ALU_Src <= '1';
			Branch <= '0';
			Jump <= '0';
			ALU_Op <= "010";
			Mem_Write <= '0';
			Mem_to_Reg <= '0';
			Reg_Write <= '1';
			
		when "101"=> ---addi
			Reg_Dst <= '0';
			Ext_Op <= '1';
			ALU_Src <= '1';
			Branch <= '0';
			Jump <= '0';
			ALU_Op <= "000";
			Mem_Write <= '0';
			Mem_to_Reg <= '0';
			Reg_Write <= '1';
			
		when "110"=> ---- lw
			Reg_Dst <= '0';
			Ext_Op <= '1';
			ALU_Src <= '1';
			Branch <= '0';
			Jump <= '0';
			ALU_Op <= "000";
			Mem_Write <= '0';
			Mem_to_Reg <= '1';
			Reg_Write <= '1';
			
		when "111"=> ---- sw
			Reg_Dst <= 'X';
			Ext_Op <= '1';
			ALU_Src <= '1';
			Branch <= '0';
			Jump <= '0';
			ALU_Op <= "000";
			Mem_Write <= '1';
			Mem_to_Reg <= 'X';
			Reg_Write <= '0';
		
		when others =>	--- and so on
			Reg_Dst <= 'X';
			Ext_Op <= 'X';
			ALU_Src <= 'X';
			Branch <= 'X';
			Jump <= '1';
			ALU_Op <= "XXX";
			Mem_Write <= '0';
			Mem_to_Reg <= 'X';
			Reg_Write <= '0';
			
	end case;
end process;		


end Unit_Control_arh;