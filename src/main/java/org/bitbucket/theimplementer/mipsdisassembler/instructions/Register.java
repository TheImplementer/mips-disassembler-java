package org.bitbucket.theimplementer.mipsdisassembler.instructions;

public enum Register {
    ZR {
        @Override
        public String toString() {
            return "$zr";
        }
    },
    AT {
        @Override
        public String toString() {
            return "$at";
        }
    },
    V0 {
        @Override
        public String toString() {
            return "$v0";
        }
    },
    V1 {
        @Override
        public String toString() {
            return "$v1";
        }
    },
    A0 {
        @Override
        public String toString() {
            return "$a0";
        }
    },
    A1 {
        @Override
        public String toString() {
            return "$a1";
        }
    },
    A2 {
        @Override
        public String toString() {
            return "$a2";
        }
    },
    A3 {
        @Override
        public String toString() {
            return "$a3";
        }
    },
    T0 {
        @Override
        public String toString() {
            return "$t0";
        }
    },
    T1 {
        @Override
        public String toString() {
            return "$t1";
        }
    },
    T2 {
        @Override
        public String toString() {
            return "$t2";
        }
    },
    T3 {
        @Override
        public String toString() {
            return "$t3";
        }
    },
    T4 {
        @Override
        public String toString() {
            return "$t4";
        }
    },
    T5 {
        @Override
        public String toString() {
            return "$t5";
        }
    },
    T6 {
        @Override
        public String toString() {
            return "$t6";
        }
    },
    T7 {
        @Override
        public String toString() {
            return "$t7";
        }
    },
    S0 {
        @Override
        public String toString() {
            return "$s0";
        }
    },
    S1 {
        @Override
        public String toString() {
            return "$s1";
        }
    },
    S2 {
        @Override
        public String toString() {
            return "$s2";
        }
    },
    S3 {
        @Override
        public String toString() {
            return "$s3";
        }
    },
    S4 {
        @Override
        public String toString() {
            return "$s4";
        }
    },
    S5 {
        @Override
        public String toString() {
            return "$s5";
        }
    },
    S6 {
        @Override
        public String toString() {
            return "$s6";
        }
    },
    S7 {
        @Override
        public String toString() {
            return "$s7";
        }
    },
    T8 {
        @Override
        public String toString() {
            return "$t8";
        }
    },
    T9 {
        @Override
        public String toString() {
            return "$t9";
        }
    },
    K0 {
        @Override
        public String toString() {
            return "$k0";
        }
    },
    K1 {
        @Override
        public String toString() {
            return "$k1";
        }
    },
    GP {
        @Override
        public String toString() {
            return "$gp";
        }
    },
    SP {
        @Override
        public String toString() {
            return "$sp";
        }
    },
    FP {
        @Override
        public String toString() {
            return "$fp";
        }
    },
    RA {
        @Override
        public String toString() {
            return "$ra";
        }
    };

    public static Register fromValue(int value) {
        switch (value) {
            case 0:
                return Register.ZR;
            case 1:
                return Register.AT;
            case 2:
                return Register.V0;
            case 3:
                return Register.V1;
            case 4:
                return Register.A0;
            case 5:
                return Register.A1;
            case 6:
                return Register.A2;
            case 7:
                return Register.A3;
            case 8:
                return Register.T0;
            case 9:
                return Register.T1;
            case 10:
                return Register.T2;
            case 11:
                return Register.T3;
            case 12:
                return Register.T4;
            case 13:
                return Register.T5;
            case 14:
                return Register.T6;
            case 15:
                return Register.T7;
            case 16:
                return Register.S0;
            case 17:
                return Register.S1;
            case 18:
                return Register.S2;
            case 19:
                return Register.S3;
            case 20:
                return Register.S4;
            case 21:
                return Register.S5;
            case 22:
                return Register.S6;
            case 23:
                return Register.S7;
            case 24:
                return Register.T8;
            case 25:
                return Register.T9;
            case 26:
                return Register.K0;
            case 27:
                return Register.K1;
            case 28:
                return Register.GP;
            case 29:
                return Register.SP;
            case 30:
                return Register.FP;
            case 31:
                return Register.RA;

        }

        throw new UnknownRegisterException(value);
    }

}
