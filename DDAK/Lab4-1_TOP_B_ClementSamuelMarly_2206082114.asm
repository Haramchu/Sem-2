.include "m8515def.inc"

//penjumlahan 
ldi R16, 10 //set register R16 = 10
ldi R17, $20 //set register R17 = heksadesimal 20
add R16, R17 //Tambah R16 dan R17, hasil di R16
// pengurangan
ldi R18, 10 //set register R18 = 10
ldi R19, $B //set register R19 = heksadesimal B
sub R19, R18 //Kurang R19 dan R18, hasil di R19
// set dan clear
st Z, R19 //set value R19 di register Z
clr R19 //reset value R19
