@echo off
rem Testen der Programme
echo Testen der Programme %1 und %2
java %1 bed1ein.txt bed1aus.txt
java %2 bed1ein.txt bed2aus.txt
fc bed1aus.txt bed2aus.txt > vergleich.txt
more vergleich.txt

