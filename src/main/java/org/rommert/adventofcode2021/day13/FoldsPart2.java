package org.rommert.adventofcode2021.day13;

import org.rommert.adventofcode2021.InputParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FoldsPart2 {

  private static final String input = "" +
      "77,882\n" +
      "656,319\n" +
      "787,478\n" +
      "0,487\n" +
      "1158,150\n" +
      "462,543\n" +
      "1073,509\n" +
      "989,886\n" +
      "1014,225\n" +
      "38,105\n" +
      "865,52\n" +
      "510,395\n" +
      "979,602\n" +
      "959,554\n" +
      "72,29\n" +
      "10,493\n" +
      "318,722\n" +
      "637,189\n" +
      "1231,406\n" +
      "152,821\n" +
      "1124,728\n" +
      "820,207\n" +
      "328,323\n" +
      "430,381\n" +
      "668,21\n" +
      "618,884\n" +
      "691,879\n" +
      "345,427\n" +
      "162,687\n" +
      "739,394\n" +
      "577,847\n" +
      "965,427\n" +
      "495,245\n" +
      "52,352\n" +
      "366,260\n" +
      "35,849\n" +
      "1285,89\n" +
      "402,677\n" +
      "254,141\n" +
      "987,327\n" +
      "808,270\n" +
      "619,401\n" +
      "1203,233\n" +
      "1096,198\n" +
      "1086,466\n" +
      "452,283\n" +
      "243,122\n" +
      "1051,299\n" +
      "692,458\n" +
      "102,285\n" +
      "815,649\n" +
      "930,660\n" +
      "319,777\n" +
      "472,506\n" +
      "291,431\n" +
      "656,166\n" +
      "527,316\n" +
      "1145,138\n" +
      "164,655\n" +
      "646,473\n" +
      "1293,203\n" +
      "142,21\n" +
      "1208,18\n" +
      "1039,885\n" +
      "502,753\n" +
      "694,109\n" +
      "776,262\n" +
      "132,88\n" +
      "1283,638\n" +
      "1014,669\n" +
      "912,403\n" +
      "1145,756\n" +
      "1069,672\n" +
      "421,243\n" +
      "1227,430\n" +
      "855,92\n" +
      "870,198\n" +
      "7,726\n" +
      "1017,14\n" +
      "738,459\n" +
      "979,327\n" +
      "1118,239\n" +
      "562,865\n" +
      "137,730\n" +
      "83,430\n" +
      "743,331\n" +
      "636,448\n" +
      "1148,547\n" +
      "254,753\n" +
      "960,462\n" +
      "102,130\n" +
      "1029,549\n" +
      "73,58\n" +
      "821,756\n" +
      "1288,812\n" +
      "199,828\n" +
      "413,40\n" +
      "82,435\n" +
      "308,395\n" +
      "107,233\n" +
      "440,368\n" +
      "569,670\n" +
      "1109,206\n" +
      "719,875\n" +
      "494,54\n" +
      "333,8\n" +
      "1004,348\n" +
      "800,164\n" +
      "411,724\n" +
      "77,210\n" +
      "900,165\n" +
      "80,340\n" +
      "649,522\n" +
      "85,33\n" +
      "1058,677\n" +
      "291,543\n" +
      "594,18\n" +
      "758,738\n" +
      "512,546\n" +
      "1279,854\n" +
      "124,312\n" +
      "619,15\n" +
      "247,138\n" +
      "624,465\n" +
      "1102,156\n" +
      "165,138\n" +
      "42,694\n" +
      "470,39\n" +
      "984,123\n" +
      "1153,43\n" +
      "132,478\n" +
      "961,5\n" +
      "932,264\n" +
      "850,358\n" +
      "763,698\n" +
      "490,655\n" +
      "191,414\n" +
      "959,106\n" +
      "336,647\n" +
      "656,347\n" +
      "1104,441\n" +
      "1067,859\n" +
      "36,436\n" +
      "733,847\n" +
      "370,428\n" +
      "97,712\n" +
      "584,276\n" +
      "252,441\n" +
      "411,170\n" +
      "131,430\n" +
      "1076,494\n" +
      "271,9\n" +
      "201,206\n" +
      "1178,478\n" +
      "572,459\n" +
      "3,567\n" +
      "803,694\n" +
      "1186,507\n" +
      "442,84\n" +
      "571,532\n" +
      "1069,525\n" +
      "194,806\n" +
      "818,871\n" +
      "1230,340\n" +
      "770,534\n" +
      "304,840\n" +
      "381,219\n" +
      "1171,513\n" +
      "338,361\n" +
      "351,340\n" +
      "336,23\n" +
      "1039,512\n" +
      "975,616\n" +
      "272,782\n" +
      "796,716\n" +
      "160,287\n" +
      "1223,10\n" +
      "201,458\n" +
      "815,245\n" +
      "678,432\n" +
      "825,3\n" +
      "420,597\n" +
      "773,400\n" +
      "572,817\n" +
      "1231,210\n" +
      "624,88\n" +
      "940,68\n" +
      "1208,609\n" +
      "855,316\n" +
      "840,605\n" +
      "1308,70\n" +
      "738,817\n" +
      "1158,821\n" +
      "577,460\n" +
      "497,182\n" +
      "629,421\n" +
      "333,522\n" +
      "131,464\n" +
      "1178,806\n" +
      "325,500\n" +
      "677,329\n" +
      "666,52\n" +
      "1218,400\n" +
      "678,502\n" +
      "1283,190\n" +
      "1014,241\n" +
      "1173,476\n" +
      "741,236\n" +
      "512,796\n" +
      "1272,120\n" +
      "1064,788\n" +
      "433,480\n" +
      "865,730\n" +
      "187,177\n" +
      "185,378\n" +
      "30,88\n" +
      "7,441\n" +
      "743,322\n" +
      "303,289\n" +
      "959,65\n" +
      "162,347\n" +
      "577,47\n" +
      "1131,822\n" +
      "1069,623\n" +
      "22,604\n" +
      "1111,380\n" +
      "370,792\n" +
      "636,619\n" +
      "1081,848\n" +
      "644,52\n" +
      "373,702\n" +
      "994,361\n" +
      "992,396\n" +
      "1265,736\n" +
      "897,301\n" +
      "929,675\n" +
      "383,830\n" +
      "234,404\n" +
      "35,718\n" +
      "349,889\n" +
      "291,463\n" +
      "1293,189\n" +
      "1047,413\n" +
      "35,243\n" +
      "358,673\n" +
      "1178,416\n" +
      "185,516\n" +
      "562,404\n" +
      "77,854\n" +
      "513,826\n" +
      "1300,493\n" +
      "616,102\n" +
      "351,554\n" +
      "494,392\n" +
      "698,68\n" +
      "1280,241\n" +
      "858,312\n" +
      "537,400\n" +
      "1223,93\n" +
      "748,628\n" +
      "940,826\n" +
      "77,434\n" +
      "489,618\n" +
      "546,560\n" +
      "497,712\n" +
      "176,145\n" +
      "239,577\n" +
      "927,134\n" +
      "798,796\n" +
      "127,474\n" +
      "430,605\n" +
      "23,849\n" +
      "813,712\n" +
      "960,716\n" +
      "753,253\n" +
      "1218,381\n" +
      "161,84\n" +
      "800,282\n" +
      "452,312\n" +
      "562,490\n" +
      "70,276\n" +
      "1289,329\n" +
      "673,33\n" +
      "1268,550\n" +
      "1280,528\n" +
      "184,21\n" +
      "480,567\n" +
      "167,873\n" +
      "488,432\n" +
      "137,612\n" +
      "637,637\n" +
      "358,653\n" +
      "961,602\n" +
      "848,705\n" +
      "154,386\n" +
      "718,725\n" +
      "1108,333\n" +
      "1064,211\n" +
      "880,381\n" +
      "741,670\n" +
      "291,203\n" +
      "416,880\n" +
      "45,736\n" +
      "1109,688\n" +
      "229,29\n" +
      "986,166\n" +
      "160,840\n" +
      "246,106\n" +
      "713,190\n" +
      "651,516\n" +
      "1131,520\n" +
      "348,404\n" +
      "825,739\n" +
      "1205,737\n" +
      "397,19\n" +
      "1223,796\n" +
      "510,613\n" +
      "1292,843\n" +
      "811,616\n" +
      "326,508\n" +
      "398,715\n" +
      "731,801\n" +
      "97,46\n" +
      "334,236\n" +
      "199,66\n" +
      "114,810\n" +
      "1226,492\n" +
      "1115,520\n" +
      "12,873\n" +
      "1111,540\n" +
      "683,704\n" +
      "549,737\n" +
      "1170,390\n" +
      "959,452\n" +
      "899,724\n" +
      "986,280\n" +
      "686,241\n" +
      "406,450\n" +
      "499,54\n" +
      "1253,190\n" +
      "818,23\n" +
      "470,289\n" +
      "1016,870\n" +
      "597,190\n" +
      "1186,835\n" +
      "160,502\n" +
      "1275,849\n" +
      "959,177\n" +
      "1061,637\n" +
      "895,658\n" +
      "348,490\n" +
      "780,731\n" +
      "139,605\n" +
      "1260,1\n" +
      "679,106\n" +
      "1058,350\n" +
      "902,211\n" +
      "1289,565\n" +
      "579,93\n" +
      "296,806\n" +
      "514,147\n" +
      "266,290\n" +
      "256,381\n" +
      "1170,626\n" +
      "841,528\n" +
      "184,263\n" +
      "947,203\n" +
      "562,46\n" +
      "632,290\n" +
      "736,106\n" +
      "184,873\n" +
      "813,3\n" +
      "288,430\n" +
      "224,802\n" +
      "1076,490\n" +
      "1150,502\n" +
      "800,612\n" +
      "219,213\n" +
      "252,350\n" +
      "185,304\n" +
      "448,632\n" +
      "708,352\n" +
      "880,130\n" +
      "868,810\n" +
      "239,317\n" +
      "1228,613\n" +
      "562,513\n" +
      "713,638\n" +
      "550,700\n" +
      "976,658\n" +
      "346,171\n" +
      "420,774\n" +
      "647,117\n" +
      "523,128\n" +
      "53,784\n" +
      "678,530\n" +
      "534,120\n" +
      "378,159\n" +
      "1193,58\n" +
      "577,40\n" +
      "664,473\n" +
      "530,835\n" +
      "562,494\n" +
      "656,534\n" +
      "1203,661\n" +
      "25,25\n" +
      "281,549\n" +
      "291,691\n" +
      "1158,243\n" +
      "1056,141\n" +
      "830,701\n" +
      "823,469\n" +
      "713,704\n" +
      "910,801\n" +
      "157,851\n" +
      "631,676\n" +
      "1173,85\n" +
      "472,730\n" +
      "1014,806\n" +
      "502,598\n" +
      "296,877\n" +
      "754,63\n" +
      "291,351\n" +
      "1091,233\n" +
      "977,451\n" +
      "383,642\n" +
      "1071,236\n" +
      "818,471\n" +
      "862,693\n" +
      "1186,59\n" +
      "1274,436\n" +
      "940,428\n" +
      "1307,775\n" +
      "862,632\n" +
      "604,750\n" +
      "139,513\n" +
      "1307,516\n" +
      "1059,353\n" +
      "1203,698\n" +
      "276,264\n" +
      "517,866\n" +
      "821,187\n" +
      "166,290\n" +
      "523,794\n" +
      "1256,110\n" +
      "587,747\n" +
      "739,464\n" +
      "182,501\n" +
      "562,266\n" +
      "840,42\n" +
      "1275,243\n" +
      "604,1\n" +
      "816,287\n" +
      "713,256\n" +
      "932,630\n" +
      "1002,395\n" +
      "567,331\n" +
      "239,236\n" +
      "989,74\n" +
      "1233,460\n" +
      "55,64\n" +
      "664,535\n" +
      "1086,381\n" +
      "331,602\n" +
      "273,110\n" +
      "899,170\n" +
      "686,689\n" +
      "1305,451\n" +
      "1086,285\n" +
      "932,735\n" +
      "114,362\n" +
      "698,694\n" +
      "1158,40\n" +
      "1218,439\n" +
      "331,327\n" +
      "698,200\n" +
      "1215,859\n" +
      "739,532\n" +
      "654,534\n" +
      "1158,624\n" +
      "1196,810\n" +
      "1200,128\n" +
      "808,73\n" +
      "513,131\n" +
      "241,525\n" +
      "408,659\n" +
      "162,547\n" +
      "92,886\n" +
      "556,63\n" +
      "259,299\n" +
      "666,742\n" +
      "592,169\n" +
      "7,117\n" +
      "440,78\n" +
      "137,282\n" +
      "616,785\n" +
      "109,219\n" +
      "70,403\n" +
      "320,236\n" +
      "412,103\n" +
      "830,822\n" +
      "899,618\n" +
      "30,653\n" +
      "1280,770\n" +
      "1144,40\n" +
      "1310,280\n" +
      "1041,862\n" +
      "947,15\n" +
      "989,8\n" +
      "1091,213\n" +
      "349,292\n" +
      "1086,428\n" +
      "862,245\n" +
      "154,508\n" +
      "346,723\n" +
      "234,754\n" +
      "413,271\n" +
      "462,207\n" +
      "328,386\n" +
      "681,869\n" +
      "186,280\n" +
      "753,176\n" +
      "731,93\n" +
      "808,262\n" +
      "445,556\n" +
      "494,840\n" +
      "557,718\n" +
      "1198,78\n" +
      "80,820\n" +
      "140,147\n" +
      "415,658\n" +
      "1171,269\n" +
      "1076,588\n" +
      "105,737\n" +
      "616,445\n" +
      "855,578\n" +
      "1005,154\n" +
      "542,646\n" +
      "1128,393\n" +
      "517,164\n" +
      "912,491\n" +
      "1067,35\n" +
      "242,791\n" +
      "209,756\n" +
      "947,463\n" +
      "547,698\n" +
      "411,324\n" +
      "644,376\n" +
      "912,715\n" +
      "810,60\n" +
      "880,428\n" +
      "956,493\n" +
      "112,78\n" +
      "947,400\n" +
      "398,65\n" +
      "1115,254\n" +
      "523,640\n" +
      "567,563\n" +
      "1255,830\n" +
      "1170,147\n" +
      "1288,432\n" +
      "851,702\n" +
      "925,414\n" +
      "160,54\n" +
      "547,661\n" +
      "1109,212\n" +
      "335,616\n" +
      "488,40\n" +
      "793,364\n" +
      "580,124\n" +
      "189,84\n" +
      "1058,322\n" +
      "716,18\n" +
      "80,422\n" +
      "102,156\n" +
      "107,698\n" +
      "1108,878\n" +
      "433,605\n" +
      "164,239\n" +
      "890,120\n" +
      "398,403\n" +
      "664,807\n" +
      "546,782\n" +
      "748,266\n" +
      "485,331\n" +
      "415,236\n" +
      "653,730\n" +
      "1124,280\n" +
      "0,575\n" +
      "1121,362\n" +
      "124,59\n" +
      "545,667\n" +
      "674,448\n" +
      "1141,155\n" +
      "899,84\n" +
      "331,154\n" +
      "515,513\n" +
      "1071,577\n" +
      "82,116\n" +
      "1101,108\n" +
      "1225,861\n" +
      "813,451\n" +
      "637,33\n" +
      "1125,378\n" +
      "1166,169\n" +
      "1038,782\n" +
      "97,624\n" +
      "249,189\n" +
      "1250,18\n" +
      "1223,523\n" +
      "499,278\n" +
      "1272,445\n" +
      "31,854\n" +
      "787,416\n" +
      "897,593\n" +
      "97,396\n" +
      "487,425\n" +
      "691,849\n" +
      "858,449\n" +
      "430,285\n" +
      "1101,351\n" +
      "189,28\n" +
      "1285,25\n" +
      "981,75\n" +
      "855,802\n" +
      "321,8\n" +
      "574,340\n" +
      "947,431\n" +
      "753,718\n" +
      "411,618\n" +
      "224,285\n" +
      "334,658\n" +
      "331,740\n" +
      "35,270\n" +
      "929,558\n" +
      "497,184\n" +
      "159,103\n" +
      "249,12\n" +
      "497,891\n" +
      "108,334\n" +
      "378,264\n" +
      "880,876\n" +
      "296,669\n" +
      "1304,236\n" +
      "830,72\n" +
      "31,488\n" +
      "167,425\n" +
      "75,801\n" +
      "1258,352\n" +
      "3,775\n" +
      "912,65\n" +
      "363,543\n" +
      "44,828\n" +
      "1007,666\n" +
      "1091,661\n" +
      "84,402\n" +
      "855,540\n" +
      "1305,73\n" +
      "743,563\n" +
      "179,520\n" +
      "1073,833\n" +
      "1026,792\n" +
      "85,147\n" +
      "656,280\n" +
      "271,512\n" +
      "889,243\n" +
      "358,241\n" +
      "632,392\n" +
      "1275,270\n" +
      "1063,138\n" +
      "87,371\n" +
      "880,285\n" +
      "880,289\n" +
      "1111,578\n" +
      "1125,516\n" +
      "1037,219\n" +
      "445,52\n" +
      "1168,873\n" +
      "462,705\n" +
      "1223,563\n" +
      "195,254\n" +
      "169,155\n" +
      "117,58\n" +
      "53,558\n" +
      "54,560\n" +
      "982,508\n" +
      "967,541\n" +
      "1205,157\n" +
      "858,344\n" +
      "619,879\n" +
      "848,257\n" +
      "514,716\n" +
      "1047,481\n" +
      "1014,877\n" +
      "114,84\n" +
      "157,43\n" +
      "1059,801\n" +
      "899,324\n" +
      "686,1\n" +
      "445,584\n" +
      "25,537\n" +
      "904,2\n" +
      "649,373\n" +
      "284,792\n" +
      "452,102\n" +
      "1250,466\n" +
      "388,92\n" +
      "1233,210\n" +
      "1001,427\n" +
      "28,413\n" +
      "201,240\n" +
      "385,414\n" +
      "803,200\n" +
      "571,500\n" +
      "517,197\n" +
      "378,735\n" +
      "370,68\n" +
      "733,47\n" +
      "646,535\n" +
      "60,466\n" +
      "1288,604\n" +
      "8,535\n" +
      "38,102\n" +
      "1148,743\n" +
      "306,289\n" +
      "1136,712\n" +
      "822,432\n" +
      "679,788\n" +
      "748,513\n" +
      "764,560\n" +
      "1230,442\n" +
      "85,658\n" +
      "1058,453\n" +
      "27,638\n" +
      "698,708\n" +
      "485,3\n" +
      "1019,351\n" +
      "899,810\n" +
      "681,421\n" +
      "1213,396\n" +
      "813,67\n" +
      "624,429\n" +
      "950,362\n" +
      "991,777\n" +
      "1064,198\n" +
      "363,203\n" +
      "366,109\n" +
      "1044,738\n" +
      "80,452\n" +
      "328,235\n" +
      "686,429\n" +
      "647,168\n" +
      "842,840\n" +
      "405,294\n" +
      "1136,633\n" +
      "1126,21\n" +
      "656,278\n" +
      "624,893\n" +
      "691,401\n" +
      "350,462\n" +
      "345,705\n" +
      "480,822\n" +
      "408,235\n" +
      "1068,791\n" +
      "1143,425\n" +
      "114,756\n" +
      "1193,584\n" +
      "134,593\n" +
      "709,784\n" +
      "686,256\n" +
      "348,714\n" +
      "273,675\n" +
      "1,164\n" +
      "600,782\n" +
      "1213,46\n" +
      "350,716\n" +
      "1196,756\n" +
      "1183,474\n" +
      "1069,719\n" +
      "1002,638\n" +
      "571,394\n" +
      "349,602\n" +
      "736,459\n" +
      "494,432\n" +
      "497,3\n" +
      "1293,33\n" +
      "269,862\n" +
      "202,16\n" +
      "694,449\n" +
      "294,810\n" +
      "764,112\n" +
      "493,707\n" +
      "199,540\n" +
      "619,45\n" +
      "1186,312\n" +
      "1126,605\n" +
      "678,604\n" +
      "1193,836\n" +
      "241,672\n" +
      "1019,203\n" +
      "28,481\n" +
      "597,638\n" +
      "1067,122\n" +
      "612,344\n" +
      "201,212\n" +
      "400,801\n" +
      "237,833\n" +
      "306,348\n" +
      "425,865\n" +
      "152,632\n" +
      "381,675\n" +
      "239,3\n" +
      "574,659\n" +
      "870,368\n" +
      "20,457\n" +
      "1067,618\n" +
      "552,156\n" +
      "686,893\n" +
      "890,196\n" +
      "502,29\n" +
      "787,794\n" +
      "82,281\n" +
      "599,700\n" +
      "1041,249\n" +
      "351,177\n" +
      "303,228\n" +
      "1225,672\n" +
      "502,821\n" +
      "391,481\n" +
      "400,93\n" +
      "430,18\n" +
      "448,262\n" +
      "1019,463\n" +
      "780,59\n" +
      "644,742\n" +
      "793,164\n" +
      "492,471\n" +
      "87,115\n" +
      "547,644\n" +
      "574,554\n" +
      "694,893\n" +
      "25,89\n" +
      "1121,866\n" +
      "490,239\n" +
      "1256,560\n" +
      "739,362\n" +
      "405,257\n" +
      "430,876\n" +
      "730,124\n" +
      "370,102\n" +
      "366,634\n" +
      "95,411\n" +
      "523,766\n" +
      "452,113\n" +
      "1236,772\n" +
      "544,10\n" +
      "162,151\n" +
      "489,187\n" +
      "663,329\n" +
      "763,196\n" +
      "195,702\n" +
      "527,578\n" +
      "766,10\n" +
      "979,119\n" +
      "930,234\n" +
      "780,582\n" +
      "\n" +
      "fold along x=655\n" +
      "fold along y=447\n" +
      "fold along x=327\n" +
      "fold along y=223\n" +
      "fold along x=163\n" +
      "fold along y=111\n" +
      "fold along x=81\n" +
      "fold along y=55\n" +
      "fold along x=40\n" +
      "fold along y=27\n" +
      "fold along y=13\n" +
      "fold along y=6";

  public static void main(String[] args) {
    String dotsInput = input.split("\n\n")[0];
    String foldsInput = input.split("\n\n")[1];

    List<Dot> dots = new InputParser<Dot>().convertInput(dotsInput, FoldsPart2::convertToDot);
    Grid grid = new Grid(dots);

    List<Fold> folds = new InputParser<Fold>().convertInput(foldsInput, FoldsPart2::convertToFolds);

//    System.out.println("Nr of dots after first fold: " + grid.rows.stream().flatMap(Collection::stream).filter(dot -> dot.equals("#")).count());
    for (Fold fold : folds) {
      grid = grid.fold(fold);

    }
    System.out.println(grid);
  }

  private static Dot convertToDot(String dotInputString) {
    String[] split = dotInputString.split(",");
    return new Dot(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
  }

  private static Fold convertToFolds(String foldInput) {
    String stripped = foldInput.replace("fold along ", "");
    String direction = stripped.split("=")[0];
    int foldingLine = Integer.parseInt(stripped.split("=")[1]);
    return new Fold(direction, foldingLine);
  }

  private static class Dot {
    int x;
    int y;

    Dot(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Dot dot = (Dot) o;
      return x == dot.x && y == dot.y;
    }

    @Override
    public int hashCode() {
      return Objects.hash(x, y);
    }
  }

  private static class Fold {
    String direction;
    int foldingLine;

    public Fold(String direction, int foldingLine) {
      this.direction = direction;
      this.foldingLine = foldingLine;
    }
  }

  private static class Grid {
    private List<Dot> dots;
    private List<List<String>> rows = new ArrayList<>();

    public Grid(List<Dot> dots) {
      this.dots = dots;

      int largestX = dots.stream().max(Comparator.comparing(dot -> dot.x)).orElse(new Dot(0,0)).x;
      int largestY = dots.stream().max(Comparator.comparing(dot -> dot.y)).orElse(new Dot(0,0)).y;

      IntStream.range(0, largestY+1).forEach(y -> {
        rows.add(new ArrayList<>());
        IntStream.range(0, largestX+1).forEach(x -> {
          if (dots.contains(new Dot(x, y))) {
            rows.get(y).add("#");
          } else {
            rows.get(y).add(".");
          }
        });
      });
    }

    Grid fold(Fold fold) {
      List<Dot> newDots = new ArrayList<>(dots);
      newDots = newDots.stream()
          .map(dot -> foldDot(dot, fold))
          .distinct()
          .collect(Collectors.toList());
      return new Grid(newDots);
    }

    private Dot foldDot(Dot dot, Fold fold) {
      if (fold.direction.equals("x") && dot.x > fold.foldingLine) {
        return new Dot(fold.foldingLine - (dot.x - fold.foldingLine), dot.y);
      }
      if (fold.direction.equals("y") && dot.y > fold.foldingLine) {
        return new Dot(dot.x, fold.foldingLine - (dot.y - fold.foldingLine));
      }
      return dot;
    }

    @Override
    public String toString() {
      StringBuilder representation = new StringBuilder();
      rows.forEach(row -> {
        row.forEach(representation::append);
        representation.append("\n");
      });
      return representation.toString();
    }
  }
}
