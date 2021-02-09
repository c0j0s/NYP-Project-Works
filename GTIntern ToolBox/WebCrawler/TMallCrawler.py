#!/usr/bin/env python3
import urllib.request, urllib.parse, http.cookiejar
import os, time, re
import http.cookies
from PIL import Image
import socket
import json
import datetime
from html.parser import HTMLParser


def ispasstime(year, month, day):
    nowtoday = datetime.datetime.today()
    passday = datetime.datetime(year, month, day)
    remaindays = (nowtoday - passday).days
    if remaindays <= 0:
        return True, -remaindays
    else:
        return False, remaindays

def listfiles(rootdir, prefix='.xml'):
    file = []
    for parent, dirnames, filenames in os.walk(rootdir):
        if parent == rootdir:
            for filename in filenames:
                if filename.endswith(prefix):
                    file.append(rootdir + '/' + filename)
            return file
        else:
            pass

def getHtml(url, daili='', postdata={}):
    filename = 'cookie.txt'
    cj = http.cookiejar.MozillaCookieJar(filename)

    if os.path.exists(filename):
        cj.load(filename, ignore_discard=True, ignore_expires=True)

    if os.path.exists('../subcookie.txt'):
        cookie = open('../subcookie.txt', 'r').read()
    else:
        cookie = 'ddd'

    proxy_support = urllib.request.ProxyHandler({'http': 'http://' + daili})

    if daili:
        print('代理:' + daili + '启动')
        opener = urllib.request.build_opener(proxy_support, urllib.request.HTTPCookieProcessor(cj),
                                             urllib.request.HTTPHandler)
    else:
        opener = urllib.request.build_opener(urllib.request.HTTPCookieProcessor(cj))

    opener.addheaders = [('User-Agent',
                          'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1.2 Safari/605.1.15'),
                         ('Referer',
                          'http://s.m.taobao.com'),
                         ('Host', 'h5.m.taobao.com'),
                         ('Cookie', cookie)]

    urllib.request.install_opener(opener)
    if postdata:
        postdata = urllib.parse.urlencode(postdata)
        html_bytes = urllib.request.urlopen(url, postdata.encode()).read()
    else:
        html_bytes = urllib.request.urlopen(url).read()

    cj.save(ignore_discard=True, ignore_expires=True)
    return html_bytes


def validateTitle(title):
    rstr = r"[\/\\\:\*\?\"\<\>\|]"  # '/\:*?"<>|'
    new_title = re.sub(rstr, "", title)
    return new_title


def createjia(path):
    try:
        os.makedirs(path)
    except:
        print('目录已经存在：' + path)

def begin():
    sangjin = '''
		TMall WebCrawler was created based on taobaoscrappy 
        that downloads product featured images from TMall.
        createdOn: 17/09/2018
        COJOS
	'''
    print(sangjin)

def outputImage(itemName, collector):
    path = "../../"+ keyword + '/' + itemName
    createjia(path)
    for index, itemUrl  in enumerate(collector):
        itemUrl = itemUrl.replace('60x60','720x720').replace('430x430','720x720')
        pic = urllib.request.urlopen(itemUrl)
        picno = time.strftime('%H%M%S', time.localtime())
        
        filenamep = path + '/' + picno + '-' + str(index)
        filenamepp = filenamep + '.jpeg'
        filess = open(filenamepp, 'wb')
        filess.write(pic.read())
        filess.close()
        print('抓到图片：' + filenamepp)

class MyHTMLParser(HTMLParser):
    def __init__(self):
        self.d = []
        super().__init__()

    def handle_starttag(self, tag, attrs):
        if(tag == 'img'):
            for name, value in attrs:
                if(name == 'src' and 'img.alicdn.com/imgextra' in value):
                    self.d.append('http:' + value)
        else:
            return

    def return_data(self):
        return self.d

#Get item link and redirect to details page
def CaptureItemImages(itemId,itemName):
    print("Custom Download Image Codes==================================================================")
    itemUrl = 'https://chaoshi.detail.tmall.com/item.htm?id=' + itemId
    html_bytes = urllib.request.urlopen(itemUrl)
    encoding = html_bytes.headers.get_content_charset('utf-8')
    html_content = html_bytes.read()
    html_text = html_content.decode(encoding)
    parser = MyHTMLParser()
    parser.feed(html_text)
    collector = parser.return_data()
    outputImage(itemName, collector)

if __name__ == '__main__':
    year = 2018
    month = 9
    day = 18
    ispass, remainday = ispasstime(year, month, day)
    if ispass:
        print("还没有过期,剩余天数:" + str(remainday))
    else:
        print("已经过期,过期天数" + str(remainday))
        time.sleep(10)
        exit(1)
    begin()

    keyword = input('Search Keyword: ')
    start = 0

    try:
        pages = int(input('需要抓取的页数（默认100页)(Enter 1000 for ranges)：'))

        if pages == 1000:
            start = int(input("starting page: "))
            end = int(input("ending page: "))
            pages = end
        elif pages > 100 or pages <= 0:
            print('页数应该在1-100之间')
            pages = 100
    except:
        pages = 100
    
    man = 2

    for page in range(start, pages):
        time.sleep(man)
        postdata = {
            'event_submit_do_new_search_auction': 1,
            'search': '提交查询',
            '_input_charset': 'utf-8',
            'topSearch': 1,
            'atype': 'b',
            'searchfrom': 1,
            'action': 'home:redirect_app_action',
            'from': 1,
            'q': keyword,
            'sst': 1,
            'n': 20,
            'buying': 'buyitnow',
            'm': 'api4h5',
            'abtest': 16,
            'wlsort': 16,
            'style': 'list',
            'closeModues': 'nav,selecthot,onesearch',
            'tab' : 'mall', #TMALL TAB
            'page': page
        }

        postdata = urllib.parse.urlencode(postdata)
        taobao = "http://s.m.taobao.com/search?" + postdata
        print("Retriving Item List:")
        print(taobao)
        print("==========================================================================================")

        try:
            html_content = getHtml(taobao)
            product = json.loads(html_content)
            onefile = product['listItem']
            for item in onefile:
                print(item['title'])
                CaptureItemImages(item['item_id'],item['title'])
        except Exception as e:
            if hasattr(e, 'code'):
                print('页面不存在或时间太长.')
                print('Error code:', e.code)
            elif hasattr(e, 'reason'):
                print("无法到达主机.")
                print('Reason:  ', e.reason)
            else:
                print(e)

    input('Tmall Product Images Retrieval Complete 请关闭窗口 [' + str(pages) + ']')