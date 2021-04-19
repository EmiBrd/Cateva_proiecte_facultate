library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity test_env is   
Port ( clk : in STD_LOGIC;          
btn : in STD_LOGIC_VECTOR (4 downto 0);          
sw : in STD_LOGIC_VECTOR (15 downto 0);          
led : out STD_LOGIC_VECTOR (15 downto 0);          
an : out STD_LOGIC_VECTOR (3 downto 0);          
cat : out STD_LOGIC_VECTOR (6 downto 0) );
end test_env;


architecture Behavioral of test_env is
----- Declararea componentelor pe care le folosim

----- componenta pentru Instruction_Decode
component Instruction_Decode is  		
Port ( clk: in std_logic;	  
			Write_Data: in std_logic_vector(15 downto 0);
			Write_A: in std_logic_vector(2 downto 0);
			Instruction: in std_logic_vector(15 downto 0);			
			Ext_Op: in std_logic;  
			Reg_Dst: in std_logic;	  
			Reg_Write: in std_logic;		 
			Ext_Imm : out std_logic_vector(15 downto 0);
			Read_Data_1: out std_logic_vector(15 downto 0);
			Read_Data_2: out std_logic_vector(15 downto 0);
			SA : out std_logic;
			Functionn : out std_logic_vector(2 downto 0) );		
end component;	


----- componenta pentru Instruction_Fetch
component Instruction_Fetch is   
 Port ( clk : in STD_LOGIC;
           reset : in STD_LOGIC;
           en : in STD_LOGIC;				
		   jump_adress : in STD_LOGIC_VECTOR (15 downto 0);
           branch_adress : in STD_LOGIC_VECTOR (15 downto 0);
           jump : in STD_LOGIC;
           pcsrc : in STD_LOGIC;    
           instruction : out STD_LOGIC_VECTOR (15 downto 0);
           next_instruction : out STD_LOGIC_VECTOR (15 downto 0));
end component;			   


----- componenta pentru Execution_Unit
component Execution_Unit is
Port( RD1: in std_logic_vector(15 downto 0);
	RD2: in std_logic_vector(15 downto 0);	
	ALUSrc: in std_logic;
	Ext_Imm: in std_logic_vector(15 downto 0);
	Func: in std_logic_vector(2 downto 0);	  
	SA: in std_logic;
	ALUOp: in std_logic_vector(2 downto 0);
	ALURes: out std_logic_vector(15 downto 0);
	Zero: out std_logic );  
end component;	


----- componenta pentru Unit_Control
component Unit_Control is	  
Port ( Instr: in std_logic_vector(2 downto 0);			 
Reg_Dst: out std_logic;			 
Ext_Op: out std_logic;			 
ALU_Src: out std_logic;		 
Branch: out std_logic;		 
bne:out std_logic;		 
Jump: out std_logic;		 
ALU_Op: out std_logic_vector(2 downto 0);		 
Mem_Write: out std_logic;		
Mem_to_Reg: out std_logic;		 
Reg_Write: out std_logic);
end component;	


----- componenta pentru Memorie_memory
component Memorie_memory is
port(	clk: in std_logic; 
		Mem_Write: in std_logic;
		ALURes : in std_logic_vector(15 downto 0);
		Write_Data: in std_logic_vector(15 downto 0);							
		Read_Data:out std_logic_vector(15 downto 0);		
		ALURes2 :out std_logic_vector(15 downto 0) );
end component;
	

----- componenta pentru MPG
component MPG is 
Port ( 	en:out std_logic;		  
		input: in std_logic;		  
		clock:in std_logic);
end component;


----- componenta pentru SSD
component SSD is	 	
Port ( clk : in STD_LOGIC;          
d0 : in STD_LOGIC_VECTOR (3 downto 0);          
d1 : in STD_LOGIC_VECTOR (3 downto 0);          
d2 : in STD_LOGIC_VECTOR (3 downto 0);          
d3 : in STD_LOGIC_VECTOR (3 downto 0);         
an : out STD_LOGIC_VECTOR (3 downto 0);         
cat : out STD_LOGIC_VECTOR (6 downto 0));
end component;


----- Declararea semnalelor folosite

----- Instruction_Decode signals
signal write_data_semnal : STD_LOGIC_VECTOR (15 downto 0);
signal ext_op_semnal : STD_LOGIC;
signal reg_dst_semnal : STD_LOGIC;
signal reg_write_semnal : STD_LOGIC;
signal ext_imm_semnal : STD_LOGIC_VECTOR (15 downto 0);
signal read_data1_semnal : STD_LOGIC_VECTOR (15 downto 0);
signal read_data2_semnal : STD_LOGIC_VECTOR (15 downto 0); 
signal sa_semnal : STD_LOGIC;
signal functionn_semnal : STD_LOGIC_VECTOR (2 downto 0);


----- Instruction_Fetch signals
signal en_semnal : STD_LOGIC;
signal reset_semnal : STD_LOGIC;
signal pcsrc_semnal : STD_LOGIC;
signal branch_adress_semnal : STD_LOGIC_VECTOR(15 downto 0);
signal jump_adress_semnal : STD_LOGIC_VECTOR(15 downto 0);
signal instruction_semnal : STD_LOGIC_VECTOR (15 downto 0);
signal next_instruction_semnal : STD_LOGIC_VECTOR (15 downto 0);


----- Execution_Unit signals
signal zero_semnal : STD_LOGIC;
signal alu_res_semnal : STD_LOGIC_VECTOR (15 downto 0);   


----- Unit_Control signals  
signal alu_src_semnal : STD_LOGIC;
signal branch_semnal : STD_LOGIC;
signal bne_semnal : STD_LOGIC;	   
signal jump_semnal : STD_LOGIC;
signal alu_op_semnal : STD_LOGIC_VECTOR (2 downto 0);
signal mem_write_semnal : STD_LOGIC;
signal mem_to_reg_semnal : STD_LOGIC;
----signal reg_write_semnal : STD_LOGIC;


----- Memorie_memory signals
signal mem_write_mpg_semnal : STD_LOGIC;
signal mem_ctrl_semnal:STD_LOGIC;
signal read_data_semnal : STD_LOGIC_VECTOR (15 downto 0);
signal alu_res2_semnal : STD_LOGIC_VECTOR (15 downto 0);		  


----- SSD signals
signal afisare_semnal : STD_LOGIC_VECTOR (15 downto 0);


-----registe pipeline 
signal R_IF_ID: std_logic_vector(31 downto 0);
signal R_ID_EX: std_logic_vector(83 downto 0);
signal R_EX_MEM: std_logic_vector(56 downto 0);
signal R_MEM_WB: std_logic_vector(36 downto 0);

signal y_mux_reg_dst: std_logic_vector(2 downto 0);



begin 
C1_M1: MPG port map(en => mem_write_mpg_semnal, input=>btn(2), clock=>clk); 

C2_Inst_Decode: Instruction_Decode port map(clk=>clk, Write_Data=> write_data_semnal, 
		Write_A=>R_MEM_WB(2 downto 0), Instruction=> R_IF_ID(15 downto 0), Ext_Op=>ext_op_semnal, 
		Reg_Dst=>reg_dst_semnal, Reg_Write=> R_MEM_WB(35), Ext_Imm=>ext_imm_semnal, 
		Read_Data_1=>read_data1_semnal, Read_Data_2=>read_data2_semnal, SA=>sa_semnal, 
		Functionn=>functionn_semnal );  	 		
	 
C3_Inst_Fetch: Instruction_Fetch port map(clk=>clk, reset=>reset_semnal, en=>en_semnal, 
		jump_adress=>jump_adress_semnal, branch_adress=> R_EX_MEM(35 downto 20), 
		jump=>jump_semnal, pcsrc=>pcsrc_semnal,
 instruction=>instruction_semnal, 
		next_instruction=>next_instruction_semnal );		  	
														 
---- process pentru R_IF_ID
process(clk)
begin
    if rising_edge(clk) then		
		R_IF_ID(15 downto 0) <= instruction_semnal;
    	R_IF_ID(31 downto 16)<= next_instruction_semnal;  	
	end if; 
end process;	
		
		
C4_Exec_Unit: Execution_Unit port map (RD1 => R_ID_EX(41 downto 26), 
		RD2 => R_ID_EX(25 downto 10), ALUSrc => R_ID_EX(75), 
		Ext_Imm => R_ID_EX(57 downto 42), Func => R_ID_EX(8 downto 6), 
		SA => R_ID_EX(9), ALUOp => R_ID_EX(78 downto 76), 
		ALURes => alu_res_semnal, zero => zero_semnal);
																
---- process pentru R_ID_EX
process (clk)
begin
	if rising_edge(clk) then
		R_ID_EX(2 downto 0) <= R_IF_ID(6 downto 4);
		R_ID_EX(5 downto 3) <= R_IF_ID(9 downto 7);
		R_ID_EX(8 downto 6) <= R_IF_ID(2 downto 0);
		R_ID_EX(9) <= R_IF_ID(3);
		R_ID_EX(25 downto 10) <= read_data2_semnal;  
		R_ID_EX(41 downto 26) <= read_data1_semnal;	
		R_ID_EX(57 downto 42) <= ext_imm_semnal;  
		R_ID_EX(73 downto 58) <= R_IF_ID(31 downto 16);		
		R_ID_EX(74) <= reg_dst_semnal;		 
		R_ID_EX(75) <= alu_src_semnal;	 
		R_ID_EX(78 downto 76) <= alu_op_semnal;	  
		R_ID_EX(79) <= mem_write_semnal;	
		R_ID_EX	(80) <= bne_semnal; 
		R_ID_EX(81) <= branch_semnal;
		R_ID_EX(82) <= reg_write_semnal;		
		R_ID_EX(83) <= mem_to_reg_semnal;				
	end if;
end process;	
	 

---- process pentru RegDst
process(R_ID_EX(74))
begin
	if R_ID_EX(74)='0' then y_mux_reg_dst <=R_ID_EX(5 downto 3);
	else y_mux_reg_dst <=R_ID_EX(2 downto 0);
	end if;
end process;
		
		
C5_Unit_Ctrl: Unit_Control port map(Instr=>R_IF_ID(15 downto 13), 
		Reg_Dst=>reg_dst_semnal,Ext_Op=>ext_op_semnal, ALU_Src=>alu_src_semnal, 
		Branch=>branch_semnal, bne => bne_semnal, Jump=>jump_semnal, 
		ALU_Op=>alu_op_semnal, Mem_Write=>mem_write_semnal, 
		Mem_to_Reg=>mem_to_reg_semnal, Reg_Write=>reg_write_semnal);		
		
---- process pentru R_EX_MEM
process(clk)									 
begin			
	R_EX_MEM(2 downto 0) <= y_mux_reg_dst;     
	R_EX_MEM(18 downto 3) <= alu_res_semnal;	
	R_EX_MEM(19) <= zero_semnal;	  
	R_EX_MEM(35 downto 20) <= R_ID_EX(57 downto 42) + R_ID_EX(73 downto 58);  
	R_EX_MEM(51 downto 36) <= R_ID_EX(25 downto 10);	
	R_EX_MEM(54 downto 52) <= R_ID_EX(81 downto 79);   -- Mem
	R_EX_MEM(56 downto 55) <= R_ID_EX(83 downto 82);   -- WB		 
end process;
		
		
C6_Memorie: Memorie_memory port map(clk=>clk, Mem_Write => mem_ctrl_semnal, 
			ALURes=>alu_res_semnal, Write_Data=> R_EX_MEM(51 downto 36), 
			Read_Data=>read_data_semnal,
 ALURes2=>alu_res2_semnal);
  
			
pcsrc_semnal <= ( R_EX_MEM(19) and R_EX_MEM(54) ) or (( not R_EX_MEM(19) ) and R_EX_MEM(53) );
jump_adress_semnal <= R_IF_ID(15 downto 0);			
mem_ctrl_semnal <= R_EX_MEM(52);


---- process pentru R_MEM_WB       
process(clk)
begin
	if rising_edge(clk) then   
		R_MEM_WB(2 downto 0) <= R_EX_MEM(2 downto 0);	
		R_MEM_WB(18 downto 3) <= read_data_semnal ; --Mem
		R_MEM_WB(34 downto 19) <= R_EX_MEM(18 downto 3);	
		R_MEM_WB(36 downto 35) <= R_EX_MEM(56 downto 55); --WB	
	end if;									  
end process;


---- mux dreapta
process ( R_MEM_WB(36) )
begin
	if R_MEM_WB(36) = '1' then
		write_data_semnal <= R_MEM_WB(34 downto 19);
	else 			 
		write_data_semnal <= R_MEM_WB(18 downto 3);
	end if;
end process;  

----------------------------------------------------------------------------
--reg_write_semnal <= reg_write_semnal or en_semnal;
--write_data_semnal <= read_data_semnal when (mem_to_reg_semnal = '1') 
--									else alu_res2_semnal;
--pcsrc_semnal <= (zero_semnal and branch_semnal) or ( (not zero_semnal) and bne_semnal);
--branch_adress_semnal <= next_instruction_semnal + ext_imm_semnal;
--jump_adress_semnal <= "000" & instruction_semnal(12 downto 0);
--mem_ctrl_semnal <=  mem_write_semnal and mem_write_mpg_semnal ;		   
------------------------------------------------------------------------------


----- MULTIPLEXOR       
	process(instruction_semnal, next_instruction_semnal ,read_data1_semnal,
		read_data2_semnal,write_data_semnal,read_data_semnal,
		alu_res_semnal,ext_imm_semnal,sw(7 downto 5))
		
	begin
		case sw(7 downto 5) is 
           	when "000" => afisare_semnal <= ext_imm_semnal;     
			when "001" => afisare_semnal <= alu_res_semnal;           
			when "010" => afisare_semnal <= read_data2_semnal;           
			when "011" => afisare_semnal <= write_data_semnal;            
			when "100" => afisare_semnal <= read_data_semnal;          
			when "101" => afisare_semnal <= read_data1_semnal;           
			when "110" => afisare_semnal <= next_instruction_semnal;         
			when "111" => afisare_semnal <= instruction_semnal;           
			when others => afisare_semnal <= x"0000";         
		end case;         
	end process;

      
C7_SSD: SSD port map( clk=>clk, d0=>afisare_semnal(3 downto 0), d1=>afisare_semnal(7 downto 4), 
		d2=>afisare_semnal(11 downto 8), d3=>afisare_semnal(15 downto 12), an=>an, cat=>cat );


end Behavioral;