

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;


entity wallace16_tb is
--  Port ( );
end wallace16_tb;

architecture Behavioral of wallace16_tb is

component wallace16 is
Port ( A : in  STD_LOGIC_VECTOR (15 downto 0);
    B : in  STD_LOGIC_VECTOR (15 downto 0);
    prod : out  STD_LOGIC_VECTOR (31 downto 0)
    );
end component;

signal X: STD_LOGIC_VECTOR(15 downto 0) := x"0000";
signal Y: STD_LOGIC_VECTOR(15 downto 0):= x"0000";
signal prod : STD_LOGIC_VECTOR(31 downto 0) := x"00000000";
signal P: STD_LOGIC_VECTOR(31 downto 0) := x"00000000";

begin

    DUT1 : wallace16 port map( A => X, B => Y, prod => P);
    
   test: process
        variable rez_rau : integer := 0;
        begin
        for I in 0 to 50 loop
            for J in 0 to 50 loop       
                X <= CONV_STD_LOGIC_VECTOR(I, 16);
                Y <= CONV_STD_LOGIC_VECTOR(J, 16);        
                prod <= CONV_STD_LOGIC_VECTOR(I*J, 32);
                wait for 10 ns;
                if (P /= prod) then 
                    rez_rau := rez_rau + 1;
                    report "Rezultat asteptat (" & STD_LOGIC'image (P(31)) & 
                    STD_LOGIC'image (P(30)) &
                    STD_LOGIC'image (P(29)) & STD_LOGIC'image (P(28)) &
                    STD_LOGIC'image (P(27)) & STD_LOGIC'image (P(26)) &
                    STD_LOGIC'image (P(25)) & STD_LOGIC'image (P(24)) &
                    STD_LOGIC'image (P(23)) & STD_LOGIC'image (P(22)) &
                    STD_LOGIC'image (P(21)) & STD_LOGIC'image (P(20)) &
                    STD_LOGIC'image (P(19)) & STD_LOGIC'image (P(18)) &
                    STD_LOGIC'image (P(17)) & STD_LOGIC'image (P(16)) &
                    STD_LOGIC'image (P(15)) & STD_LOGIC'image (P(14)) &
                    STD_LOGIC'image (P(13)) & STD_LOGIC'image (P(12)) &
                    STD_LOGIC'image (P(11)) & STD_LOGIC'image (P(10)) &
                    STD_LOGIC'image (P(9)) & STD_LOGIC'image (P(8)) &
                    STD_LOGIC'image (P(7)) & STD_LOGIC'image (P(6)) & 
                    STD_LOGIC'image (P(5)) & STD_LOGIC'image (P(4)) & 
                    STD_LOGIC'image (P(3)) & STD_LOGIC'image (P(2)) & 
                    STD_LOGIC'image (P(1)) & STD_LOGIC'image (P(0)) 
                    
                    & ") /= Valoare obtinuta (" & STD_LOGIC'image (prod(31)) & 
                    STD_LOGIC'image (prod(30)) &
                    STD_LOGIC'image (prod(29)) & STD_LOGIC'image (prod(28)) &
                    STD_LOGIC'image (prod(27)) & STD_LOGIC'image (prod(26)) & 
                    STD_LOGIC'image (prod(25)) & STD_LOGIC'image (prod(24)) &
                    STD_LOGIC'image (prod(23)) & STD_LOGIC'image (prod(22)) &
                    STD_LOGIC'image (prod(21)) & STD_LOGIC'image (prod(20)) &
                    STD_LOGIC'image (prod(19)) & STD_LOGIC'image (prod(18)) &
                    STD_LOGIC'image (prod(17)) & STD_LOGIC'image (prod(16)) &
                    STD_LOGIC'image (prod(15)) & STD_LOGIC'image (prod(14)) &
                    STD_LOGIC'image (prod(13)) & STD_LOGIC'image (prod(12)) &
                    STD_LOGIC'image (prod(11)) & STD_LOGIC'image (prod(10)) &
                    STD_LOGIC'image (prod(9)) & STD_LOGIC'image (prod(8)) &
                    STD_LOGIC'image (prod(7)) & STD_LOGIC'image (prod(6)) & 
                    STD_LOGIC'image (prod(5)) & STD_LOGIC'image (prod(4)) & 
                    STD_LOGIC'image (prod(3)) & STD_LOGIC'image (prod(2)) & 
                    STD_LOGIC'image (prod(1)) & STD_LOGIC'image (prod(0)) & 
                    ") la t = " & TIME'image (now)
                    severity ERROR;
                end if;                
            end loop;
        end loop;
        
        for I in 4975 to 5025 loop
                    for J in 4975 to 5025 loop       
                        X <= CONV_STD_LOGIC_VECTOR(I, 16);
                        Y <= CONV_STD_LOGIC_VECTOR(J, 16);        
                        prod <= CONV_STD_LOGIC_VECTOR(I*J, 32);
                        wait for 10 ns;
                        if (P /= prod) then 
                            rez_rau := rez_rau + 1;
                            report "Rezultat asteptat (" & STD_LOGIC'image (P(31)) & 
                            STD_LOGIC'image (P(30)) &
                            STD_LOGIC'image (P(29)) & STD_LOGIC'image (P(28)) &
                            STD_LOGIC'image (P(27)) & STD_LOGIC'image (P(26)) &
                            STD_LOGIC'image (P(25)) & STD_LOGIC'image (P(24)) &
                            STD_LOGIC'image (P(23)) & STD_LOGIC'image (P(22)) &
                            STD_LOGIC'image (P(21)) & STD_LOGIC'image (P(20)) &
                            STD_LOGIC'image (P(19)) & STD_LOGIC'image (P(18)) &
                            STD_LOGIC'image (P(17)) & STD_LOGIC'image (P(16)) &
                            STD_LOGIC'image (P(15)) & STD_LOGIC'image (P(14)) &
                            STD_LOGIC'image (P(13)) & STD_LOGIC'image (P(12)) &
                            STD_LOGIC'image (P(11)) & STD_LOGIC'image (P(10)) &
                            STD_LOGIC'image (P(9)) & STD_LOGIC'image (P(8)) &
                            STD_LOGIC'image (P(7)) & STD_LOGIC'image (P(6)) & 
                            STD_LOGIC'image (P(5)) & STD_LOGIC'image (P(4)) & 
                            STD_LOGIC'image (P(3)) & STD_LOGIC'image (P(2)) & 
                            STD_LOGIC'image (P(1)) & STD_LOGIC'image (P(0)) 
                            
                            & ") /= Valoare obtinuta (" & STD_LOGIC'image (prod(31)) & 
                            STD_LOGIC'image (prod(30)) &
                            STD_LOGIC'image (prod(29)) & STD_LOGIC'image (prod(28)) &
                            STD_LOGIC'image (prod(27)) & STD_LOGIC'image (prod(26)) & 
                            STD_LOGIC'image (prod(25)) & STD_LOGIC'image (prod(24)) &
                            STD_LOGIC'image (prod(23)) & STD_LOGIC'image (prod(22)) &
                            STD_LOGIC'image (prod(21)) & STD_LOGIC'image (prod(20)) &
                            STD_LOGIC'image (prod(19)) & STD_LOGIC'image (prod(18)) &
                            STD_LOGIC'image (prod(17)) & STD_LOGIC'image (prod(16)) &
                            STD_LOGIC'image (prod(15)) & STD_LOGIC'image (prod(14)) &
                            STD_LOGIC'image (prod(13)) & STD_LOGIC'image (prod(12)) &
                            STD_LOGIC'image (prod(11)) & STD_LOGIC'image (prod(10)) &
                            STD_LOGIC'image (prod(9)) & STD_LOGIC'image (prod(8)) &
                            STD_LOGIC'image (prod(7)) & STD_LOGIC'image (prod(6)) & 
                            STD_LOGIC'image (prod(5)) & STD_LOGIC'image (prod(4)) & 
                            STD_LOGIC'image (prod(3)) & STD_LOGIC'image (prod(2)) & 
                            STD_LOGIC'image (prod(1)) & STD_LOGIC'image (prod(0)) & 
                            ") la t = " & TIME'image (now)
                            severity ERROR;
                        end if;                
                    end loop;
                end loop;  
                
        for I in 9950 to 9999 loop
                            for J in 9950 to 9999 loop       
                                X <= CONV_STD_LOGIC_VECTOR(I, 16);
                                Y <= CONV_STD_LOGIC_VECTOR(J, 16);        
                                prod <= CONV_STD_LOGIC_VECTOR(I*J, 32);
                                wait for 10 ns;
                                if (P /= prod) then 
                                    rez_rau := rez_rau + 1;
                                    report "Rezultat asteptat (" & STD_LOGIC'image (P(31)) & 
                                    STD_LOGIC'image (P(30)) &
                                    STD_LOGIC'image (P(29)) & STD_LOGIC'image (P(28)) &
                                    STD_LOGIC'image (P(27)) & STD_LOGIC'image (P(26)) &
                                    STD_LOGIC'image (P(25)) & STD_LOGIC'image (P(24)) &
                                    STD_LOGIC'image (P(23)) & STD_LOGIC'image (P(22)) &
                                    STD_LOGIC'image (P(21)) & STD_LOGIC'image (P(20)) &
                                    STD_LOGIC'image (P(19)) & STD_LOGIC'image (P(18)) &
                                    STD_LOGIC'image (P(17)) & STD_LOGIC'image (P(16)) &
                                    STD_LOGIC'image (P(15)) & STD_LOGIC'image (P(14)) &
                                    STD_LOGIC'image (P(13)) & STD_LOGIC'image (P(12)) &
                                    STD_LOGIC'image (P(11)) & STD_LOGIC'image (P(10)) &
                                    STD_LOGIC'image (P(9)) & STD_LOGIC'image (P(8)) &
                                    STD_LOGIC'image (P(7)) & STD_LOGIC'image (P(6)) & 
                                    STD_LOGIC'image (P(5)) & STD_LOGIC'image (P(4)) & 
                                    STD_LOGIC'image (P(3)) & STD_LOGIC'image (P(2)) & 
                                    STD_LOGIC'image (P(1)) & STD_LOGIC'image (P(0)) 
                                    
                                    & ") /= Valoare obtinuta (" & STD_LOGIC'image (prod(31)) & 
                                    STD_LOGIC'image (prod(30)) &
                                    STD_LOGIC'image (prod(29)) & STD_LOGIC'image (prod(28)) &
                                    STD_LOGIC'image (prod(27)) & STD_LOGIC'image (prod(26)) & 
                                    STD_LOGIC'image (prod(25)) & STD_LOGIC'image (prod(24)) &
                                    STD_LOGIC'image (prod(23)) & STD_LOGIC'image (prod(22)) &
                                    STD_LOGIC'image (prod(21)) & STD_LOGIC'image (prod(20)) &
                                    STD_LOGIC'image (prod(19)) & STD_LOGIC'image (prod(18)) &
                                    STD_LOGIC'image (prod(17)) & STD_LOGIC'image (prod(16)) &
                                    STD_LOGIC'image (prod(15)) & STD_LOGIC'image (prod(14)) &
                                    STD_LOGIC'image (prod(13)) & STD_LOGIC'image (prod(12)) &
                                    STD_LOGIC'image (prod(11)) & STD_LOGIC'image (prod(10)) &
                                    STD_LOGIC'image (prod(9)) & STD_LOGIC'image (prod(8)) &
                                    STD_LOGIC'image (prod(7)) & STD_LOGIC'image (prod(6)) & 
                                    STD_LOGIC'image (prod(5)) & STD_LOGIC'image (prod(4)) & 
                                    STD_LOGIC'image (prod(3)) & STD_LOGIC'image (prod(2)) & 
                                    STD_LOGIC'image (prod(1)) & STD_LOGIC'image (prod(0)) & 
                                    ") la t = " & TIME'image (now)
                                    severity ERROR;
                                end if;                
                            end loop;
                        end loop;
        
        if rez_rau > 0 then
            report "Numar erori = " & Integer'image(rez_rau); 
        else
            report "Nu sunt erori";
         end if;
        wait for 10 ns;
    end process test;


end Behavioral;
