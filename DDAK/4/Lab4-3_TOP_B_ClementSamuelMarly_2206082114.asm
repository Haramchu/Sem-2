.include "m8515def.inc"

//set variabel sesuai soal dan register
.def RB = R3
.def SK = R4
.def CC = R5
.def ED = R6
.def ANTRE = R7
.def TPA = R8
.def TPB = R9
.def TPC = R10
.def TPD = R11
.def cek = R17
.def count = R18
.def temporary = R19

START:
	//inisiasi stack
	ldi R16,low(RAMEND)
	out SPL,R16
	ldi R16,high(RAMEND)
	out SPH,R16

Set_harga:
	//set harga
	ldi R16, 7 //set R16 = 7
	mov RB, R16 // copy nilai R16 ke RB (R3)
	ldi R16, 5 //set R16 = 5
	mov SK, R16 // copy nilai R16 ke SK (R4)
	ldi R16, 5 //set R16 = 5
	mov CC, R16 // copy nilai R16 ke CC (R5)
	ldi R16, 4 //set R16 = 4
	mov ED, R16 // copy nilai R16 ke ED (R6)

Main:
	//set awal
	ldi R16, 10 //set nilai antrian 10 (2114%8 + 8)
	mov ANTRE, R16 // copy nilai R16 ke ANTRE (R7)
	rjmp Antrian // lompat ke Antrian

Check:
	//cek urutan apakah ganjil atau genap
	inc count //jumlah count ditambah 1
	mov cek, count //copy nilai count ke cek
	mov temporary, count //copy nilai count ke temporary
	andi cek, 1 //cek dimodulus 2 kemudian dicek apakah 0 atau 1 (Validasi genap atau ganjil)
	breq Kelipatan_enam //jika cek = 0 atau genap
	brne Kelipatan_tiga //jika cek = 1 atau ganjil

Kelipatan_tiga:
	//menyesuaikan urutan dengan paket yang sesuai
	cpi temporary, 3 //Mengecek apakah nilai temporary sama dengan 3
	breq Paket_B //jika temporary sama dengan 3, masuk ke PAKET_B
	brlo Paket_D //jika temporary tidak sama dengan 3, masuk ke PAKET_D
	subi temporary, 3 //temporary dikurang 3 sampai sisa
	rjmp Kelipatan_tiga //loop

Kelipatan_enam:
	//menyesuaikan urutan dengan paket yang sesuai
	cpi temporary, 6 //Mengecek apakah nilai temporary sama dengan 6
	breq Paket_C //jika temporary sama dengan 3, masuk ke PAKET_B
	brlo Paket_A //jika temporary tidak sama dengan 3, masuk ke PAKET_D
	subi temporary, 6 //temporary dikurang 3 sampai sisa
	rjmp Kelipatan_enam //loop

Paket_A:
	//masukkan nilai
	mov R16, RB //copy nilai RB ke R16
	add R16, CC //tambah RB dengan CC di R16
	push R16 //push nilai R16 ke stack
	rjmp Antrian

Paket_B:
	mov R16, SK //copy nilai SK ke R16
	add R16, ED //tambah SK dengan ED di R16
	push R16 //push nilai R16 ke stack
	rjmp Antrian

Paket_C:
	mov R16, RB //copy nilai RB ke R16
	add R16, ED //tambah RB dengan ED di R16
	push R16 //push nilai R16 ke stack
	rjmp Antrian

Paket_D:
	mov R16, SK //copy nilai SK ke R16
	add R16, CC //tambah SK dengan ED di R16
	push R16 //push nilai R16 ke stack
	rjmp Antrian

Antrian:
	//ulang loop apabila jumlah yang telah dihitung belum sesuai dengan jumlah antrian
	cp count, ANTRE //Mengecek nilai count dengan ANTRE
	brne Check //Jika nilai count tidak sama dengan ANTRE, masuk ke Check
	breq Calculate //jika nilai count sama dengan ANTRE, masuk ke Calculate

Cek_harga:
	//cek harga paket
	mov cek, count //copy nilai count ke check
	mov temporary, count ///copy nilai count ke temporary
	andi cek, 1 //validasi genap atau ganjil
	breq Cek_enam //jika genap, cek apakah kelipatan 6
	brne Cek_tiga //jika ganjil, cek apakah kelipatan 3

//validasi tipe paket
Cek_tiga:
	cpi temporary, 3 //Mengecek apakah nilai temporary sama dengan 3 (kelipatan 3)
	breq Tambah_Paket_B //jika iya, masuk ke Tambah_Paket_B
	brlo Tambah_Paket_D //jika tidak, masuk ke Tambah_Paket_D
	subi temporary, 3 //temporary dikurang 3 sampai sisa
	rjmp Cek_tiga //loop

Cek_enam:
	cpi temporary, 6 //Mengecek apakah nilai temporary sama dengan 6 (kelipatan 6)
	breq Tambah_Paket_C ///jika iya, masuk ke Tambah_Paket_C
	brlo Tambah_Paket_A //jika tidak, masuk ke Tambah_Paket_A
	subi temporary, 6 //temporary dikurang 6 sampai sisa
	rjmp Cek_enam //loop

//masukkan nilai paket ke dalam register yang sesuai
Tambah_Paket_A:
	pop R16 //ambil value R16
	add TPA, R16 //tambah TPA dan R16 di TPA
	dec count //decrement count
	rjmp Calculate //masuk kembali ke Calculate

Tambah_Paket_B:
	pop R16 //ambil value R16
	add TPB, R16 //tambah TPB dan R16 di TPB
	dec count //count dikurang 1
	rjmp Calculate //masuk kembali ke Calculate

Tambah_Paket_C:
	pop R16 //ambil value R16
	add TPC, R16 //tambah TPC dan R16 di TPC
	dec count //count dikurang 1
	rjmp Calculate //masuk kembali ke Calculate

Tambah_Paket_D:
	pop R16 //ambil value R16
	add TPD, R16 //tambah TPD dan R16 di TPD
	dec count ///count dikurang 1
	rjmp Calculate //masuk kembali ke Calculate

Calculate:
	//apabila antrian sudah habis
	cpi count, 0 //Mengecek apakah nilai count sama dengan 0
	breq forever //jika iya, program diakhiri
	brne Cek_harga //jika tidak, masuk ke Cek_harga

forever:
	rjmp forever // akhir program
