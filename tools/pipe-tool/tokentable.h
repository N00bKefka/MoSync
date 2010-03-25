/* Copyright (C) 2009 Mobile Sorcery AB

This program is free software; you can redistribute it and/or modify it under
the terms of the GNU General Public License, version 2, as published by
the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
for more details.

You should have received a copy of the GNU General Public License
along with this program; see the file COPYING.  If not, write to the Free
Software Foundation, 59 Temple Place - Suite 330, Boston, MA
02111-1307, USA.
*/

TOKENTAB(".sourcefile", 		dir_sourcefile,	        0)
TOKENTAB(".ctor",               dir_ctor,               0)
TOKENTAB(".dtor",               dir_dtor,               0)
TOKENTAB(".sourcedir",          dir_sourcedir,          0)
TOKENTAB(".line",               dir_line,               0)
TOKENTAB(".dlab",               dir_dlab,               0)
TOKENTAB(".library",            dir_library,            0)
TOKENTAB(".exit",               dir_exit,               0)
TOKENTAB(".asm_on",             dir_asm_on,             0)
TOKENTAB(".asm_off",            dir_asm_off,            0)
TOKENTAB(".AllowFarBranches",   dir_AllowFarBranches,   0)
TOKENTAB(".bp",                 dir_bp,                 0)
TOKENTAB(".localscope",         dir_localscope,         0)
TOKENTAB(".func",               dir_func,               0)
TOKENTAB(".local",              dir_local,              0)
TOKENTAB(".vec_elt",            dir_vec_elt,            0)
TOKENTAB(".syscall",            dir_syscall,            0)
TOKENTAB(".lfile",              dir_lfile,              0)
TOKENTAB(".org",                dir_org,                0)
TOKENTAB(".set",                dir_set,                0)
TOKENTAB(".debug",              dir_debug,              0)
TOKENTAB(".info",               dir_info,               0)
TOKENTAB(".list",               dir_list,               0)
TOKENTAB(".show",               dir_show,               0)
TOKENTAB(".print",          	dir_print,              0)
TOKENEQL(".?",					dir_prnt,				dir_print)
TOKENTAB(".ifdef",              dir_ifdef,              0)
TOKENTAB(".ifndef",             dir_ifndef,             0)

TOKENTAB(".ifdefglobal",        dir_ifdefglobal,        0)
TOKENTAB(".ifndefglobal",       dir_ifndefglobal,       0)


TOKENTAB(".if",                 dir_if,                 0)
TOKENTAB(".while",              dir_while,              0)
TOKENTAB(".enum",               dir_enum,               0)
TOKENTAB(".breakpoint",         dir_breakpoint,         0)
TOKENTAB(".globals_in_code",    dir_globals_in_code,    0)
TOKENTAB(".globl",				dir_globl,			    0)
TOKENEQL(".global",				dir_global,				dir_globl)
TOKENEQL(".weak",				dir_weak,				dir_globl)
TOKENTAB(".extern",      		dir_extern,             0)
TOKENEQL(".extrn",       		dir_extrn,				dir_extern)
TOKENTAB(".byte",               dir_byte,               0)
TOKENTAB(".half",               dir_half,               0)
TOKENEQL(".short",              dir_short,       dir_half)
TOKENTAB(".word",               dir_word,               0)
TOKENEQL(".long",               dir_long,        dir_word)
TOKENTAB(".space",              dir_space,              0)
TOKENTAB(".string",             dir_string,             0)
TOKENEQL(".ascii",              dir_ascii,     dir_string)
TOKENTAB(".comm",               dir_comm,               0)
TOKENTAB(".lcomm",              dir_lcomm,              0)
TOKENTAB(".align",              dir_align,              0)
TOKENTAB(".section",            dir_section,            0)
TOKENTAB(".ctors",              dir_ctors,              0)
TOKENTAB(".dtors",              dir_dtors,              0)
TOKENTAB(".bss",                dir_bss,                0)
TOKENTAB(".data",               dir_data,               0)
TOKENTAB(".code", 		        dir_code,		        0)
TOKENEQL(".text",       		dir_text,				dir_code)
TOKENTAB(".ent",                dir_ent,                0)
TOKENTAB(".eof",                dir_eof,                0)
TOKENTAB(".end",                dir_end,                0)
TOKENTAB(".stabs",              dir_stabs,              0)
TOKENTAB(".stabn",              dir_stabn,              0)
TOKENTAB(".stabd",              dir_stabd,              0)
TOKENTAB(".model",              dir_model,              0)


TOKENTAB("syscall",             opc_syscall,            0)
TOKENTAB("break",               opc_break,              0)
TOKENTAB("case",                opc_case,               0)
TOKENTAB("push",                opc_push,               0)
TOKENTAB("call",                opc_call,               0)
TOKENTAB("divu",                opc_divu,               0)
TOKENTAB("ld.b",                opc_ldb,                0)
TOKENTAB("ld.h",                opc_ldh,                0)
TOKENTAB("div",                 opc_div,                0)
TOKENTAB("pop",                 opc_pop,                0)
TOKENTAB("and",                 opc_and,                0)
TOKENTAB("xor",                 opc_xor,                0)
TOKENTAB("sll",                 opc_sll,                0)
TOKENTAB("sra",                 opc_sra,                0)
TOKENTAB("srl",                 opc_srl,                0)
TOKENTAB("add",                 opc_add,                0)
TOKENTAB("mul",                 opc_mul,                0)
TOKENTAB("sub",                 opc_sub,                0)
TOKENTAB("ret",                 opc_ret,                0)
TOKENTAB("nop",                 opc_nop,                0)
TOKENTAB("brk",                 opc_brk,                0)
TOKENTAB("not",                 opc_not,                0)
TOKENTAB("neg",                 opc_neg,                0)
TOKENTAB("ld",                  opc_ld,                 0)
TOKENTAB("jc",                  opc_jc,                 0)
TOKENTAB("jp",                  opc_jp,                 0)
TOKENTAB("or",                  opc_or,                 0)
TOKENTAB("xb",                  opc_xb,                 0)
TOKENTAB("xh",                  opc_xh,                 0)

