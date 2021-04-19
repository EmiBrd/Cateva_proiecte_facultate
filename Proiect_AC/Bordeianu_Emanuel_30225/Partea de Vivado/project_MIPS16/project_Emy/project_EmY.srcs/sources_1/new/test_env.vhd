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
  
port(
 clk: in std_logic; 
		Mem_Write: in std_logic;
		ALURes : in std_logic_vector(15 downto 0);
		Write_Data: in std_logic_vector(15 downto 0);							
		Read_Data:out std_logic_vector(15 downto 0);
			
		ALURes2 :out std_logic_vector(15 downto 0)
	);

end component;


	

----- componenta pentru MPG


component MPG is
   
Port ( en:out std_logic;
		  
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

signal branch_adress_semnal : STD_LOGIC_VECTOR(15 downto 0):=x"5678";

signal jump_adress_semnal : STD_LOGIC_VECTOR(15 downto 0):=x"ABCD";

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





begin 
   	

C1_M1: MPG port map(en => mem_write_mpg_semnal, input=>btn(2), clock=>clk); 
		
		


C2_Inst_Decode: Instruction_Decode port map(clk=>clk, Write_Data=>write_data_semnal, 
		Instruction=>instruction_semnal, Ext_Op=>ext_op_semnal, 
		Reg_Dst=>reg_dst_semnal, Reg_Write=>reg_write_semnal, Ext_Imm=>ext_imm_semnal, 
		Read_Data_1=>read_data1_semnal, Read_Data_2=>read_data2_semnal, SA=>sa_semnal, 
		Functionn=>functionn_semnal );  

	 
		
		
C3_Inst_Fetch: Instruction_Fetch port map(clk=>clk, reset=>reset_semnal, en=>en_semnal, 
		jump_adress=>jump_adress_semnal, branch_adress=>branch_adress_semnal, 
		jump=>jump_semnal, pcsrc=>pcsrc_semnal,
 instruction=>instruction_semnal, 
		next_instruction=>next_instruction_semnal );
	
		

C4_Exec_Unit: Execution_Unit port map (RD1 => read_data1_semnal, 
		RD2 => read_data2_semnal, ALUSrc => alu_src_semnal, 
		Ext_Imm => ext_imm_semnal, Func => functionn_semnal, 
		SA => sa_semnal, ALUOp => alu_op_semnal, 
		ALURes => alu_res_semnal, zero => zero_semnal);

		
		
C5_Unit_Ctrl: Unit_Control port map(Instr=>instruction_semnal(15 downto 13), 
		Reg_Dst=>reg_dst_semnal,Ext_Op=>ext_op_semnal, ALU_Src=>alu_src_semnal, 
		Branch=>branch_semnal, bne => bne_semnal, Jump=>jump_semnal, 
		ALU_Op=>alu_op_semnal, Mem_Write=>mem_write_semnal, 
		Mem_to_Reg=>mem_to_reg_semnal, Reg_Write=>reg_write_semnal);
        
  
		
		
C6_Memorie: Memorie_memory port map(clk=>clk, Mem_Write => mem_ctrl_semnal, 
			ALURes=>alu_res_semnal, Write_Data=>read_data2_semnal, 
			Read_Data=>read_data_semnal,
 ALURes2=>alu_res2_semnal);
      

			


reg_write_semnal <= reg_write_semnal or en_semnal;


write_data_semnal <= read_data_semnal when (mem_to_reg_semnal = '1') 
									else alu_res2_semnal;


pcsrc_semnal <= (zero_semnal and branch_semnal) or ( (not zero_semnal) and bne_semnal);


branch_adress_semnal <= next_instruction_semnal + ext_imm_semnal;

jump_adress_semnal <= "000" & instruction_semnal(12 downto 0);


mem_ctrl_semnal <=  mem_write_semnal and mem_write_mpg_semnal ;


  


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
	
		d2=>afisare_semnal(11 downto 8), d3=>afisare_semnal(15 downto 12),
		an=>an, cat=>cat );



end Behavioral;
											 



