import { User } from "types/interface";
import { create } from "zustand"; // 꼭 zustand 확인할 것

interface LoginUserStore {
  loginUser: User | null;
  setLoginUser: (loginUser: User) => void;
  resetLogiUser: () => void; 
}

const useLoginUserStore = create<LoginUserStore>(set => ({
  loginUser: null,
  setLoginUser: loginUser => set(state => ({ ...StaticRange, loginUser })),
  resetLogiUser: () => set(state => ({ ...state, loginUser: null }))
}));

export default useLoginUserStore;