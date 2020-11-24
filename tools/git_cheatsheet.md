
{unstage} --(add)--> {stage} --(commit)--> {repo} --(push)--> {github (remote repo)}

# regred for commit -m 
`git reset HEAD~1 ?{--filename}` unstage without commit
`git revert HEAD ?{--filename}` unstage with commit 

