autocmd filetype cpp nnoremap <F5> :w <bar> !g++-4.8 -ulimit -Wall -Wno-unused-result -std=c++11   -O2   % -o %:r && ./%:r <CR>
syntax on
set nu
set clipboard=unnamedplus
set mouse=a
set tabstop=2
set autoindent
set shiftwidth=2
set softtabstop=2
set smarttab
set expandtab
set smartcase

set t_Co=256
set background=dark
colorscheme monokai 

autocmd filetype java nnoremap <F5> :w <bar> !javac % && java -enableassertions %:r <CR>
autocmd filetype python nnoremap <F5> :w <bar> !python % <CR>
autocmd filetype perl nnoremap <F5> :w <bar> !perl % <CR>
autocmd filetype scala nnoremap <F5> :w <bar> !scala % <CR>
autocmd filetype go nnoremap <F5> :w <bar> !go build % && ./%:r <CR>
