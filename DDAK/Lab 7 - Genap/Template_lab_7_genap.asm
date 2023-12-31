;====================================================================
; Processor		: ATmega8515
; Compiler		: AVRASM
;====================================================================

;====================================================================
; DEFINITIONS
;====================================================================

.include "m8515def.inc"
.def temp = r16
; TODO
.def count_jadwal =
.def libur_flag =
.def persiapan =
.def count_libur =
.def defense_flag =

;====================================================================
; RESET and INTERRUPT VECTORS
;====================================================================

;
.org $00
rjmp MAIN
; TODO - DEFINE INTERRUPT ADDRESS

;====================================================================
; CODE SEGMENT
;====================================================================

MAIN:
	ldi count_jadwal, ;todo

INIT_STACK:
	; TODO - define the need for a stack pointer

INIT_LED:
	; TODO - set every port needed

INIT_EXT_INTERRUPT:
	; TODO - set the need for external interrupt

INIT_INT_INTERRUPT:
	; TODO - set the need for internal interrupt

CHECK_STATUS:
	cpi defense_flag, 1
	breq DEFENSE
	sbrs libur_flag, 0
	rjmp CHECK_JADWAL
    cpi count_libur, 0
    breq NORMAL
	rjmp LIBUR

NORMAL:
    ; TODO
    rjmp CHECK_JADWAL

CHECK_JADWAL:
	cpi count_jadwal, ...
	... JADWAL_02
	... JADWAL_01
	... JADWAL_03

JADWAL_01:
	sbrc persiapan, 0
	rcall PERSIAPAN_JADWAL_01
	ldi temp, ...
	out ..., temp
	out ..., temp
	ldi temp, ...
	out ..., temp
	rcall DELAY
	rjmp CHECK_STATUS

JADWAL_02:
	sbrc persiapan, 0
	rcall ; TODO
	ldi temp, ...
	out ..., temp
	ldi temp, ...
	out ..., temp
	out ..., temp
	rcall DELAY
	rjmp ; TODO

JADWAL_03:
	sbrc persiapan, 0
	rcall ; TODO
	ldi temp, ...
	out ..., temp
	ldi temp, ...
	out ..., temp
	out ..., temp
	rcall DELAY
	rjmp ; TODO

PERSIAPAN_JADWAL_01:
	ldi temp, ...
	out ..., temp
	rcall DELAY
	clr persiapan
	ret

PERSIAPAN_JADWAL_02:
	ldi temp, ...
	out ..., temp
	rcall DELAY
	clr persiapan
	ret

PERSIAPAN_JADWAL_03:
	ldi temp, ...
	out ..., temp
	rcall DELAY
	clr persiapan
	ret


DEFENSE:
	; TODO - SET LAMPS & CONFIGURATION FOR LIBUR MODE
	rcall DELAY
	rjmp CHECK_STATUS

LIBUR:
	; TODO - SET LAMPS & CONFIGURATION FOR LIBUR MODE
	rcall DELAY
	rjmp CHECK_STATUS

INTERRUPT_SWITCH_JADWAL:
	; TODO - HANDLE INTERRUPT

INTERRUPT_DEFENSE:
	; TODO - HANDLE INTERRUPT

INTERRUPT_LIBUR:
    ; TODO - HANDLE INTERRUPT

EXIT_INTERRUPT:
	reti

DELAY:
; Generated by delay loop calculator
; at http://www.bretmulvey.com/avrdelay.html
;
; DELAY_CONTROL 20 000 cycles
; 2ms at 8.0 MHz

	ldi  r22, 99
	ldi  r23, 249
	
L2: 
	dec  r23
	brne L2
	dec  r22
	brne L2
	nop
	ret
