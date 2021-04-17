@echo off
set xv_path=C:\\Xilinx\\Vivado\\2016.4\\bin
call %xv_path%/xsim multiplier_CPA_16_tb_behav -key {Behavioral:sim_1:Functional:multiplier_CPA_16_tb} -tclbatch multiplier_CPA_16_tb.tcl -log simulate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
