

echo +++++++++ 正在将秒杀项目上传到github.....................
echo +++++++++复制前端文件...............
rm -rf FrontEnd
mkdir FrontEnd
cp -r  /nginx/microblog  /home/lgj/aProject/aRealPrj/mocroblog/FrontEnd  

echo  ++++++++git add  .
git add .
echo  ++++++++git add finish 
echo  ++++++++git commit -m 
git commit -m "$1"
echo  ++++++++上传备注： $1

git push origin master
echo ++++++++上传成功！





