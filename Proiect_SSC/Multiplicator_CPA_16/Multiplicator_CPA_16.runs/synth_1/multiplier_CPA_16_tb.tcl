# 
# Synthesis run script generated by Vivado
# 

set_msg_config -id {HDL 9-1061} -limit 100000
set_msg_config -id {HDL 9-1654} -limit 100000
create_project -in_memory -part xc7a50tcsg324-1

set_param project.singleFileAddWarning.threshold 0
set_param project.compositeFile.enableAutoGeneration 0
set_param synth.vivado.isSynthRun true
set_property webtalk.parent_dir C:/D/An3/An3_sem1_exerc/SSC/SSC_proiect/Multiplicator_CPA_16/Multiplicator_CPA_16.cache/wt [current_project]
set_property parent.project_path C:/D/An3/An3_sem1_exerc/SSC/SSC_proiect/Multiplicator_CPA_16/Multiplicator_CPA_16.xpr [current_project]
set_property default_lib xil_defaultlib [current_project]
set_property target_language Verilog [current_project]
set_property ip_output_repo c:/D/An3/An3_sem1_exerc/SSC/SSC_proiect/Multiplicator_CPA_16/Multiplicator_CPA_16.cache/ip [current_project]
set_property ip_cache_permissions {read write} [current_project]
read_vhdl -library xil_defaultlib {
  C:/D/An3/An3_sem1_exerc/SSC/SSC_proiect/Multiplicator_CPA_16/Multiplicator_CPA_16.srcs/sources_1/new/full_adder.vhd
  C:/D/An3/An3_sem1_exerc/SSC/SSC_proiect/Multiplicator_CPA_16/Multiplicator_CPA_16.srcs/sources_1/new/carry_prop_adder.vhd
  C:/D/An3/An3_sem1_exerc/SSC/SSC_proiect/Multiplicator_CPA_16/Multiplicator_CPA_16.srcs/sources_1/new/multiplier_CPA_16.vhd
  C:/D/An3/An3_sem1_exerc/SSC/SSC_proiect/Multiplicator_CPA_16/Multiplicator_CPA_16.srcs/sources_1/new/multiplier_CPA_16_tb.vhd
}
foreach dcp [get_files -quiet -all *.dcp] {
  set_property used_in_implementation false $dcp
}
read_xdc C:/D/An3/An3_sem1_exerc/SSC/SSC_proiect/Multiplicator_CPA_16/Multiplicator_CPA_16.srcs/constrs_1/imports/SSC_lab/Nexys4DDR_Master.xdc
set_property used_in_implementation false [get_files C:/D/An3/An3_sem1_exerc/SSC/SSC_proiect/Multiplicator_CPA_16/Multiplicator_CPA_16.srcs/constrs_1/imports/SSC_lab/Nexys4DDR_Master.xdc]


synth_design -top multiplier_CPA_16_tb -part xc7a50tcsg324-1


write_checkpoint -force -noxdef multiplier_CPA_16_tb.dcp

catch { report_utilization -file multiplier_CPA_16_tb_utilization_synth.rpt -pb multiplier_CPA_16_tb_utilization_synth.pb }
