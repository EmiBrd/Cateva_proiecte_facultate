 library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;  

entity Execution_Unit is
Port( RD1: in std_logic_vector(15 downto 0);
	RD2: in std_logic_vector(15 downto 0);	
	ALUSrc: in std_logic;
	Ext_Imm: in std_logic_vector(15 downto 0);
	Func: in std_logic_vector(2 downto 0);	  
	SA: in std_logic;
	ALUOp: in std_logic_vector(2 downto 0);
	ALURes: out std_logic_vector(15 downto 0);
	Zero: out std_logic );  	 
	
end Execution_Unit;


architecture Execution_Unit_arh of Execution_Unit is

signal ALU_ctrl: std_logic_vector(2 downto 0) := "000";  
signal Alu_select:std_logic_vector(15 downto 0) := X"0000";
signal ALURes2:std_logic_vector(15 downto 0) := X"0000";
signal Zero2: std_logic;

begin
							
		process(RD2, ALUSrc, Ext_Imm)
        begin
            if( ALUSrc = '1') then 
				Alu_select <= Ext_Imm;
            else
				Alu_select <= RD2;
            end if;
        end process;

	process(ALUOp, Func)
	begin
		case (ALUOp) is
			when "000"=>
				case (Func) is
					when "000"=> ALU_ctrl <= "000"; 	--- add
					when "001"=> ALU_ctrl <= "001";  --- sub
					when "010"=> ALU_ctrl <= "010";	--- sll
					when "011"=> ALU_ctrl <= "011";	--- srl
					when "100"=> ALU_ctrl <= "100";	--- and
					when "101"=> ALU_ctrl <= "101";	--- or
					when "110"=> ALU_ctrl <= "110";	--- xor	  
					when "111" => ALU_ctrl <= "111"; -- nand
					when others=> ALU_ctrl <= "000";
				end case;
				
			when "001"=> ALU_ctrl <= "000";	---- addi
			when "010"=> ALU_ctrl <= "001";	---- beq
			when "101"=> ALU_ctrl <= "100";	---- andi
			when "110"=> ALU_ctrl <= "101";	---- ori
			when "111"=> ALU_ctrl <= "110";	---- jmp
			when others=> ALU_ctrl <= "000";	
		end case;
	end process;
	
	
	process(ALU_ctrl, RD1, Alu_select, SA)
	begin
		case(ALU_ctrl) is
			when "000" => ALURes2 <= RD1 + Alu_select;   		
			when "001" => ALURes2 <= RD1 - Alu_select;	
			when "010" => 	
					if ( SA = '0' ) then 
						 ALURes2 <= RD1;
					else 		   
						ALURes2 <= "0" & RD1(15 downto 1);
					end if;
			
			when "011" => 	if( SA = '0' ) then
								ALURes2 <= RD1;
							else	   
								ALURes2 <= RD1(14 downto 0) & "0";	
							end if;
								
			when "100" => ALURes2 <= RD1 and Alu_select;					
			when "101" => ALURes2 <= RD1 or Alu_select;								
			when "110" => ALURes2 <= RD1 xor Alu_select;	
			when "111" => ALURes2 <= RD1 nand Alu_select;								   				
			when others => ALURes2 <= "0000000000000000";	
		end case;
		
		if(ALURes2 = "0000000000000000") then
			Zero2 <= '1';
		else
			Zero2 <= '0';
		end if;

end process;

Zero <= Zero2;	--- zero final
ALURes <= ALURes2;	--- ALURes

end Execution_Unit_arh;