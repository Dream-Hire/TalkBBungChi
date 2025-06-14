import * as path from 'path';
import react from '@vitejs/plugin-react';
import { defineConfig } from 'vite';
import svgr from 'vite-plugin-svgr';

export default defineConfig({
  plugins: [react(), svgr()],
  server: {
    port: 3000,
  },
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },
  build: {
    outDir: 'dist',
    emptyOutDir: true,  // 기존 파일 제거 후 빌드
  },
  base: '/', // ? 중요: Nginx에서 index.html 경로를 맞추기 위해 필요
});
