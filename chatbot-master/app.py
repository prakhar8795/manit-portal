#General Libraries
import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from nltk.stem import WordNetLemmatizer
from nltk.corpus import wordnet as wn
import re
import aiml
import subprocess
import os
import argparse
from MyKernel import MyKernel
from time import sleep
from flask import Flask, request
#end of general libraries


DEBUG = True
SHOW_MATCHES = True

def course_code(sentence):
	try:
		words = re.compile('\w+').findall(sentence)
		new_sentence = ""
		for index, word in enumerate(words):
			if (word == "cse") and (index < len(words)-1) and (words[index+1].isdigit()):
				new_sentence = new_sentence + word
			else:
				new_sentence = new_sentence + word + " "
		return new_sentence
	except:
		return sentence

def remove_aiml_char(sentence):
    try:
        new_sentence = sentence.replace("_","")
        new_sentence = new_sentence.replace("*","")
        return new_sentence
    except:
        return sentence

dict = {'NN': 'NOUN', 'JJ': 'ADJ'}
dict['NNS'] = 'NOUN'
dict['NNP'] = 'NOUN'
dict['NNPS'] = 'NOUN'
dict['PRP'] = 'NOUN'
dict['PRP$'] = 'NOUN'
dict['RB'] = 'ADV'
dict['RBR'] = 'ADV'
dict['RBS'] = 'ADV'
dict['VB'] = 'VERB'
dict['VBD'] = 'VERB'
dict['VBG'] = 'VERB'
dict['VBN'] = 'VERB'
dict['VBP'] = 'VERB'
dict['VBZ'] = 'VERB'
dict['WRB'] = 'ADV'

grade_codes = ["ap","aa","ab","bb","bc","cc","cd","dd","dx","fr"]

BOT_INFO = {
    "name": "Da Vinci Bot",
    "birthday": "March 29th 2017",
    "location": "Bhopal",
    "master": "Alan Turing",
    "website":"https://www.manit.ac.in",
    "gender": "Male",
    "age": "20",
    "size": "",
    "religion": "Hindu",
    "party": "Oh yes,of course anytime!"
}

k = MyKernel()
k.learn("aiml/standard/std-startup.xml")
k.respond("LOAD AIML B")

for key,val in BOT_INFO.items():
	k.setBotPredicate(key,val)

def buttonClicked(inputString):
        # mythread = threading.Thread(target=self.show_popup)
        # mythread.start()
        # mythread.join()  
        # print "clicked"
        myinput = inputString
        sentence = myinput.lower()
        sentence = course_code(sentence)
        stop_words = set(stopwords.words('english'))

        word_tokens = word_tokenize(sentence)

        filtered_sentence = [w for w in word_tokens if not w in stop_words]
        # print filtered_sentence
        temp = nltk.pos_tag(filtered_sentence)
        new_sentence = ""
        for i in temp:
            try:
                z = i[1]
                if (dict[z] != None):
                    part_speech = dict[z]
                else:
                    part_speech = 'NOUN'

                if(part_speech == 'NOUN'):
                    word = wn.morphy(i[0],wn.NOUN)
                elif(part_speech == 'VERB'):
                    word = wn.morphy(i[0],wn.VERB)
                elif(part_speech == 'ADV'):
                    word = wn.morphy(i[0],wn.ADV)
                elif(part_speech == 'ADJ'):
                    word = wn.morphy(i[0],wn.ADJ)
                word1 = wn.synsets(word)[0].lemmas()[0].name()
                if i[0] in grade_codes:
                    word1 = i[0]
            except:
                word1 = i[0]
            new_sentence = new_sentence+" "+word1.lower()
            new_sentence = remove_aiml_char(new_sentence)

        #----------uncomment to debug----------------------
        if DEBUG:
            #printing first output
            matchedPattern = k.matchedPattern(myinput)
            response = k.respond(myinput)
            try:
                if SHOW_MATCHES:
                    print "Matched Pattern: "
                    print k.formatMatchedPattern(matchedPattern[0])
                    pattern = k.getPredicate("topic",'_global')
                    print "TOPIC:",pattern
                else:
                    print "-------------------------"
            except:
                print "No match found"
            print "Normal Response: ",response

            # printing after processing
            print "--------------------------------"
            print "new_sentence :",new_sentence
            matchedPattern = k.matchedPattern(new_sentence)
            response = k.respond(new_sentence)
            try:
                if SHOW_MATCHES:
                    print "Matched Pattern: "
                    print k.formatMatchedPattern(matchedPattern[0])
                    pattern = k.getPredicate("topic",'_global')
                    print "TOPIC:",pattern
                else:
                    print "-------------------------"
            except:
                print "No match found"
            print "New Response: ",response

        #--------------------------------------------------
        response = k.respond(myinput)
        response1 = k.respond(new_sentence)
        if response1 != "" and response1[0] == '$':
            response = response1[1:]
        # print response  
        # mythread = threading.Thread(target=self.dismiss_popup)
        # mythread.start()   
        # mythread.join()       
        # self.show_popup()
        return response



app = Flask(__name__)


@app.route('/chatbot',methods=['GET'])
def main():
    print (request.args.get('userInput'))
    res = buttonClicked(request.args.get('userInput'))
    callback = request.args.get('callback');
    return '{0}({1})'.format(callback, {'a':res, 'b':2})


if __name__ == "__main__":
    app.run()
