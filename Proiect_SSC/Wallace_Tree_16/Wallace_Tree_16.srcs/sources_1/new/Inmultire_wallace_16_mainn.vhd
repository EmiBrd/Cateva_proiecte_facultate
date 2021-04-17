

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity Inmultire_wallace_16_mainn is
Port ( X : in STD_LOGIC_VECTOR(15 downto 0);
    Y : in STD_LOGIC_VECTOR(15 downto 0);
    Rst : in STD_LOGIC;
    Start : in STD_LOGIC;
    Start2 : in STD_LOGIC;
    Rez_term : in std_logic; 
    Clk : in STD_LOGIC;
    X_primeste_X : out STD_LOGIC;
    Y_primeste_X : out STD_LOGIC;
    An: out STD_LOGIC_VECTOR(7 downto 0);
    Seg: out STD_LOGIC_VECTOR(7 downto 0);
    Term : out STD_LOGIC
    );
end Inmultire_wallace_16_mainn;


architecture Behavioral of Inmultire_wallace_16_mainn is

component wallace16 is
Port ( A : in  STD_LOGIC_VECTOR (15 downto 0);
    B : in  STD_LOGIC_VECTOR (15 downto 0);
    prod : out  STD_LOGIC_VECTOR (31 downto 0)
    );
end component;

component displ7seg is
 Port ( Clk  : in  STD_LOGIC;
         Rst  : in  STD_LOGIC;
         Data : in  STD_LOGIC_VECTOR (31 downto 0);   -- datele pentru 8 cifre (cifra 1 din stanga: biti 31..28)
         An   : out STD_LOGIC_VECTOR (7 downto 0);    -- selectia anodului activ
         Seg  : out STD_LOGIC_VECTOR (7 downto 0));   -- selectia catozilor (segmentelor) cifrei active
end component;

component MPG is
 Port ( clk : in std_logic;
   rst,input:in std_logic;
   q_out:out std_logic
);
end component;

signal nr1, nr2 : STD_LOGIC_VECTOR(15 downto 0) := x"0000";
signal Data_aux : STD_LOGIC_VECTOR(31 downto 0);
signal P : STD_LOGIC_VECTOR(31 downto 0);
signal en_start, en_start2, en_rez : std_logic := '0';
signal x_primeste_x_aux, y_primeste_x_aux, term_aux : std_logic := '0';
signal rst_aux : std_logic := '0';

begin

mul_CPA1 : wallace16 port map(A => nr1 , B => nr2, prod => P);                    

MPG_start : MPG port map(clk=>Clk ,rst=>'0',input=>Start, q_out=> en_start);

MPG_start2 : MPG port map(clk=>Clk ,rst=>'0',input=>Start2, q_out=> en_start2);

MPG_term : MPG port map(clk=>Clk ,rst=>'0',input=>Rez_term, q_out=> en_rez);

MPG_reset : MPG port map(clk=>Clk ,rst=>'0',input=>Rst, q_out=> rst_aux);

    p1 : process (clk, en_start, en_start2, en_rez)
    begin
        if rising_edge(clk) then
            if Rst = '1' then 
                Y_primeste_X_aux <= '0';
                X_primeste_X_aux <= '0';
                term_aux <= '0';
                Data_aux <= x"00000000";
            end if;
            if en_start2 = '1' then
                nr2 <= X;
                Y_primeste_X_aux <= '1';
                X_primeste_X_aux <= '0';
                term_aux <= '0';
                Data_aux <= x"0000" & nr2;
            end if;
            if en_start = '1' then
                nr1 <= X;
                X_primeste_X_aux <= '1';
                Y_primeste_X_aux <= '0';
                term_aux <= '0';
                Data_aux <= x"0000" & nr1;
            end if;
            if en_rez = '1' then
                term_aux <= '1';
                X_primeste_X_aux <= '0';
                Y_primeste_X_aux <= '0';
                Data_aux <= P;
            end if;
        end if;
        Y_primeste_X <= Y_primeste_X_aux;
        X_primeste_X <= X_primeste_X_aux;
        term <= term_aux;
    end process;


display1 : displ7seg port map(Clk => Clk, Rst => '0', Data => Data_aux,
                An => An, Seg => Seg);


end Behavioral;
