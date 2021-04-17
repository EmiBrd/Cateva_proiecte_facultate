@echo off
set xv_path=C:\\Xilinx\\Vivado\\2016.4\\bin
call %xv_path%/xelab  -wto a55bba7296d34e269d3c6edf1c4469e3 -m64 --debug typical --relax --mt 2 -L xil_defaultlib -L secureip --snapshot multiplier_CPA_16_tb_behav xil_defaultlib.multiplier_CPA_16_tb -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
