	.file	"/u/jojen2/cs321-hw1-978457164/p15.s"
	.globl	_Main_main
_Main_main:
	pushl	%ebp
	movl	%esp,%ebp
	subl	$4,%esp
	movl	$55,%eax
	movl	%eax,-4(%ebp)
	Div is not yet implemented
	movl	%eax,-4(%ebp)
	movl	%ebp,%esp
	popl	%ebp
	ret
