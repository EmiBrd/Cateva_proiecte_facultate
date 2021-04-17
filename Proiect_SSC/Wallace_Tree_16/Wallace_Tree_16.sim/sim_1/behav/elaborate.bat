@echo off
set xv_path=C:\\Xilinx\\Vivado\\2016.4\\bin
call %xv_path%/xelab  -wto 61f20e09ad804c13910ecb13a6dbdf96 -m64 --debug typical --relax --mt 2 -L xil_defaultlib -L secureip --snapshot wallace16_tb_behav xil_defaultlib.wallace16_tb -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
