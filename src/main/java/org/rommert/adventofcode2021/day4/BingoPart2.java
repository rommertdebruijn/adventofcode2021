package org.rommert.adventofcode2021.day4;

import org.rommert.adventofcode2021.InputParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BingoPart2 {

  private static String cardInput = "91  5 64 81 34\n" +
      "15 99 31 63 65\n" +
      "45 39 54 93 83\n" +
      "51 14 23 86 32\n" +
      "19 22 16 13  3\n" +
      "\n" +
      "20 83 38 85 70\n" +
      "69 12 14 26 84\n" +
      "19 76 45 78 99\n" +
      "22 80 90 33 46\n" +
      "75 31 21  6 28\n" +
      "\n" +
      "22 52 65 75  2\n" +
      "91 12 45 18 94\n" +
      "38 66 85 39  1\n" +
      "24 36 55 74  3\n" +
      "89 14 79 99 48\n" +
      "\n" +
      "19 58 95 22  6\n" +
      "48 28 57 30 72\n" +
      "12 67 15 37 18\n" +
      "33  1 49 90 60\n" +
      "35 41 47 11 84\n" +
      "\n" +
      "89 27 65 68 19\n" +
      "38 83 21 81 91\n" +
      "67 61 87 30 10\n" +
      "36 45 66 56  4\n" +
      "82 71 44 96 90\n" +
      "\n" +
      "29 84 40 62 92\n" +
      "69 39  2  1 56\n" +
      " 6 20 27 97 54\n" +
      "14 28 65 52 32\n" +
      "31 58 36 95  8\n" +
      "\n" +
      "84 46  4 15 66\n" +
      "28 99 17 68 37\n" +
      "57  6  8 64 12\n" +
      "18 23 24 45 34\n" +
      "39 55 85  5 19\n" +
      "\n" +
      "56 63 13 76 35\n" +
      " 7 94 24 23 67\n" +
      "60 44 12 25 71\n" +
      "78 19 42 87 39\n" +
      " 2  3 69 26 22\n" +
      "\n" +
      "75 43 42 45 77\n" +
      "57 59 88  3 49\n" +
      "27 23 64 60 12\n" +
      "53 85 83 52 21\n" +
      "24 50  6 31 80\n" +
      "\n" +
      "34 43 75 69 59\n" +
      "93 23 46 48  2\n" +
      " 4 15 61  5 96\n" +
      "80 97 27 51 33\n" +
      "25 44 79 32 17\n" +
      "\n" +
      "16 67 81 96 42\n" +
      "21 79 45 78 28\n" +
      "39 91 86 51 88\n" +
      "62 94 99 41 27\n" +
      "84 30 22 74  8\n" +
      "\n" +
      "19 14 10 76 17\n" +
      "13  1 73  0 55\n" +
      "97 26 98  4 15\n" +
      "44 87 61 80 11\n" +
      "74 16 94 45 75\n" +
      "\n" +
      "20 36 25 81  7\n" +
      "93 40 83 71 32\n" +
      "56  9 61 95 17\n" +
      "79 48 72 87 15\n" +
      " 0 12 78 13 54\n" +
      "\n" +
      "76 42 24 16 20\n" +
      "94 89 38 85  4\n" +
      "96 99 45 78 84\n" +
      "88 55 66 34 63\n" +
      "47 95 27  6 17\n" +
      "\n" +
      "75 24 23  4 81\n" +
      "95 58 40 52 13\n" +
      " 3  2 45 50 73\n" +
      "98 39 92 14 28\n" +
      "12  9 41 10 17\n" +
      "\n" +
      "42 95 23 93 88\n" +
      "29 14 38 80 48\n" +
      "55 72 69 64 34\n" +
      "18 58  8 96 30\n" +
      "16  7 20  0 46\n" +
      "\n" +
      "25 22 65 17 19\n" +
      "85  0 73 46 10\n" +
      "43 35 12 59 51\n" +
      "76 40 42 94 78\n" +
      "79 28 91 39 31\n" +
      "\n" +
      "84 70 34 12  8\n" +
      "51  7 93 66 42\n" +
      " 0 39  4 22 38\n" +
      " 5 86 65 82 44\n" +
      "74 19 98 35 53\n" +
      "\n" +
      "71 20 72 83 34\n" +
      "19  6 88 51 27\n" +
      "98 42 56 62 69\n" +
      "49 11 99 50 21\n" +
      "90 18 47 15 91\n" +
      "\n" +
      " 2 93  5 60 98\n" +
      "71 73 12 50 18\n" +
      "30 87  9 69 21\n" +
      "23 90 92 49  1\n" +
      "99 34 11 33 58\n" +
      "\n" +
      "18 83 82 92 72\n" +
      "16 19 73 25 55\n" +
      "35 20  8 41 39\n" +
      "45 71 70 23 30\n" +
      " 4 15 57 58 80\n" +
      "\n" +
      " 2 50 95 34 83\n" +
      "72 62 15 89 12\n" +
      "20 39 65 68 33\n" +
      "92 67 60  1 56\n" +
      "29 86 23 37 45\n" +
      "\n" +
      "36 86 92 58 74\n" +
      "47 39 30 40 56\n" +
      "17 18 14  4 97\n" +
      "20 28 62 94 80\n" +
      "88 84 98  0 38\n" +
      "\n" +
      "85 88 47 43 44\n" +
      "82 52 60 61 42\n" +
      "39 87 33 36 14\n" +
      "40 49 13 20  4\n" +
      "86 92 19 90  2\n" +
      "\n" +
      " 5 36 48 66 72\n" +
      "44 98 87 24 79\n" +
      "60 19  3 15 47\n" +
      "42 25 93 57 88\n" +
      "32 14 62 64 34\n" +
      "\n" +
      "65 72 80 96 20\n" +
      "82 38 52 19 29\n" +
      "39 70 95 54 84\n" +
      "71 85 32 16 92\n" +
      "14 81 28 23 17\n" +
      "\n" +
      "20 89 61 53 83\n" +
      "87 41 13 66 78\n" +
      "88 22 58 56 94\n" +
      "60 12 15 17 28\n" +
      "70 49 36 64 11\n" +
      "\n" +
      "96 73 81 30 98\n" +
      "40 15 33 37 54\n" +
      " 0 76 22 13 36\n" +
      "17 49 44 27 51\n" +
      "26  6 92 25  1\n" +
      "\n" +
      "25 95 96 31 22\n" +
      "34 42 41 60 23\n" +
      "75 32 77 81 15\n" +
      "17 79  6 11 94\n" +
      "97 85 66 44 43\n" +
      "\n" +
      " 1 18 75 85  0\n" +
      "21 35 22 64 55\n" +
      "60 50 57 47 67\n" +
      "68 61 56 63 90\n" +
      "49 65 33 59 30\n" +
      "\n" +
      "75 11 64 28 37\n" +
      "58 82 40 59  4\n" +
      "24 77 69 22  9\n" +
      "60 36 89  7 68\n" +
      "53  2 94 43 72\n" +
      "\n" +
      "34 11 92 55 25\n" +
      "72 68 76 67 24\n" +
      " 8 50 56 59 12\n" +
      "90 91 86 13 29\n" +
      "57 79 16 26 42\n" +
      "\n" +
      "94 49 69 66 52\n" +
      "47 65 43 38 81\n" +
      "64 40 10 32 13\n" +
      "70  3 74 83 37\n" +
      "56 59 55 78 39\n" +
      "\n" +
      "87 95 51 32 35\n" +
      "21  4 80  8 24\n" +
      " 1 13 43 58 92\n" +
      "47 69 49 37 16\n" +
      "65 74 31 57 28\n" +
      "\n" +
      "28 14 10 30 32\n" +
      "13 50 19 41 77\n" +
      "76 61 46 97 15\n" +
      "36 17 62 74 57\n" +
      "26 66 99 51 64\n" +
      "\n" +
      "40 11 56 66 67\n" +
      "26 81 45 13 51\n" +
      "80 82 57 87 61\n" +
      "46 16 17 21 72\n" +
      "42 95 68 33 83\n" +
      "\n" +
      "54 94 23 42 53\n" +
      "36  4 43 92 87\n" +
      " 8 59 69 62  1\n" +
      "86 98  9  2 73\n" +
      " 7 12 83 67 24\n" +
      "\n" +
      "56 79 71 38  6\n" +
      "17 20 28 80 57\n" +
      "84 22 83 61 50\n" +
      " 0 52 36 92  4\n" +
      "70 55 63 12  3\n" +
      "\n" +
      "18  9 47 22 32\n" +
      "21 95 45 50 34\n" +
      "84 69 19 16 36\n" +
      "85 62 71 88 98\n" +
      "55 80 37 63 83\n" +
      "\n" +
      "19 51 99 30 48\n" +
      " 9  3 89 22 46\n" +
      " 6 76 15 95 90\n" +
      "53 12 62 67 98\n" +
      "74 31 16 54 65\n" +
      "\n" +
      "42 58 20 27 64\n" +
      "18 57 76 82 43\n" +
      "72  8 61 60 12\n" +
      "88 13 49 91 78\n" +
      "96 83 51 86 14\n" +
      "\n" +
      "49 79 90 21 85\n" +
      "39 10 78  1 47\n" +
      "66 70 86  9 74\n" +
      "31 62 50 58 25\n" +
      "19 73 28  2 55\n" +
      "\n" +
      "78 29 34 87 77\n" +
      " 2 71  8 51 98\n" +
      "62 84 89 27 85\n" +
      "90 94 44 30 93\n" +
      "28 68 31 20 75\n" +
      "\n" +
      "73 68 76 51 87\n" +
      "91 65  9 29 15\n" +
      "42 35 74 72 25\n" +
      "52 45 31 53 97\n" +
      "23 19 54 83 41\n" +
      "\n" +
      "90 10 58 40 89\n" +
      "76 71 85 56 65\n" +
      "25 80 19  0 28\n" +
      "60  7 47 98 67\n" +
      "63 81 37 91 35\n" +
      "\n" +
      "78 56 29 50 32\n" +
      "79 92 38 49 45\n" +
      "70 80 76 86 64\n" +
      "53 22 20 66 60\n" +
      "77 25 84 93 88\n" +
      "\n" +
      "83 64 56 13 88\n" +
      "63 25 65  1 45\n" +
      " 7 61 57 37 75\n" +
      "58 96 84 91 28\n" +
      "43 69 32 74 99\n" +
      "\n" +
      "39 54 73 61 46\n" +
      "60 44 71 33 89\n" +
      " 4 67 10  2 96\n" +
      "58  8 20 68 93\n" +
      "56 17 38 94 34\n" +
      "\n" +
      "89 59 36 31 94\n" +
      "91 34 83 55 54\n" +
      "99 40 30 56 11\n" +
      "49 33 42 39 70\n" +
      " 2 64 17 25 87\n" +
      "\n" +
      "25 97  2 45 93\n" +
      "88 92 78 32 69\n" +
      "67  6 47 37 96\n" +
      "51 40 82 57 99\n" +
      "30 38 29 55 85\n" +
      "\n" +
      "65  7 29 39 58\n" +
      "28 27 20  0 84\n" +
      "32 31 44  4 57\n" +
      "17 79 92 33 41\n" +
      "94 35 22 74  5\n" +
      "\n" +
      "62 77 48 50 97\n" +
      "39 91 42 73 88\n" +
      "23  3  6 17 15\n" +
      "13 37 25 65 16\n" +
      "94 12 54  0 14\n" +
      "\n" +
      "45 55  3  2 40\n" +
      "49 58 43  5 72\n" +
      "60 46 33 85 79\n" +
      "61 34 27 92 35\n" +
      "54 51 65 23 17\n" +
      "\n" +
      " 4 70 20 80 94\n" +
      " 1 74 53 83 10\n" +
      "91 16 46 63 75\n" +
      "39 90 81 27 17\n" +
      "38 19 62 44 92\n" +
      "\n" +
      "16 25 99 27 66\n" +
      "80  3 38 10 11\n" +
      "52 98 83  7 76\n" +
      "70 29 79 96 42\n" +
      "41 74 68 36 82\n" +
      "\n" +
      "50 73 72 40 93\n" +
      "60 96 52 74 63\n" +
      "51 30 87 41 82\n" +
      "44  4 59  0  1\n" +
      "43 62 10 13 58\n" +
      "\n" +
      "92 98 20 94 95\n" +
      "37 57 74 47 38\n" +
      "36 89 97 14 59\n" +
      "81 46 66 55 35\n" +
      "82 62 42 12 28\n" +
      "\n" +
      " 5 81  8 23 36\n" +
      "61  7 94 95 74\n" +
      "91 71  6 32 65\n" +
      "79 30 67 49 37\n" +
      " 9 44 84 58 82\n" +
      "\n" +
      "19 56 13 80 65\n" +
      "79 59 57 10 44\n" +
      "78 97 49 26 70\n" +
      "72 95 18 14 96\n" +
      " 3 99 33 32 41\n" +
      "\n" +
      " 5 42 18 14 57\n" +
      "19 62  6 92 96\n" +
      " 2 58 75 49  9\n" +
      "24 48 55 60 67\n" +
      "91 37 27 34 59\n" +
      "\n" +
      "65 11 18 78  6\n" +
      "30 76 61 27 15\n" +
      "49 25 59 17 29\n" +
      "20 24 99 37 40\n" +
      "93 90 68 71 22\n" +
      "\n" +
      "34 15 77 98 70\n" +
      "58 21 54  8 60\n" +
      "49 32 62 56 87\n" +
      "30 17 83 85 23\n" +
      "80 48 10 26 24\n" +
      "\n" +
      "39 90 21  4 71\n" +
      "53 55 24 27 28\n" +
      " 5 87 54 66 20\n" +
      "32 18 10 77 59\n" +
      "99 80 91 13 34\n" +
      "\n" +
      " 8 80 50 63 42\n" +
      " 9 40 51 83 93\n" +
      "91 89 32 74 41\n" +
      "28 65 29 33 55\n" +
      "59 31 99 36 19\n" +
      "\n" +
      " 4 96 28 51 14\n" +
      "84 39 98 59 15\n" +
      "87 45 65 83 78\n" +
      " 2  8 49  0 36\n" +
      " 7  9 64 58 74\n" +
      "\n" +
      "23 10 70 59 36\n" +
      "55 84 18 40 50\n" +
      "47 28 58 72  8\n" +
      "24 60 34 44 79\n" +
      "77 73 89 95 92\n" +
      "\n" +
      "83 77 61 81 36\n" +
      "50 19 31 69 35\n" +
      "15 39 75 18 43\n" +
      "98 51 25 54 63\n" +
      " 9 74 91  6 88\n" +
      "\n" +
      "40 81 16 82 25\n" +
      "66 57 98 14  6\n" +
      "92 71 27 39 87\n" +
      "17 12 20 23 47\n" +
      "83 22  4 90 73\n" +
      "\n" +
      "92 22 17 80 55\n" +
      "20 35 34 62 88\n" +
      "23 47 56 14  8\n" +
      "45 52  2 36 67\n" +
      "28 69  5 93 44\n" +
      "\n" +
      "99 88 14 47  2\n" +
      "22 51 15 57 68\n" +
      "33 13 71 80  9\n" +
      "63 69  7 90 74\n" +
      "17 87 36 46 16\n" +
      "\n" +
      "67 27  7 65 73\n" +
      "13 87 16 99  6\n" +
      "33 30 96 41 23\n" +
      " 0 10  9 98 72\n" +
      "53 52 81 18 24\n" +
      "\n" +
      "49 89 44  9 35\n" +
      "71 67 21 32  7\n" +
      "55 50 37 34 64\n" +
      "31  8  1 17 73\n" +
      "48 27 81 39  0\n" +
      "\n" +
      "81 40 50 48 47\n" +
      "33 14 76 56 25\n" +
      "62 93 57 78 85\n" +
      "51 44  2 43 16\n" +
      " 0 74 27 20 22\n" +
      "\n" +
      "96 30 47 33 57\n" +
      "91 70 31 45 84\n" +
      "20 13  2 36 28\n" +
      " 6 53 55 81  7\n" +
      "72 27 44 12 39\n" +
      "\n" +
      "27 94 70 54 72\n" +
      "62 39 46 89 29\n" +
      "43 12 86 35  2\n" +
      "66 34 55 82 78\n" +
      "57  0 32 21 79\n" +
      "\n" +
      "46 18 29 82  5\n" +
      " 7 52 10 12 45\n" +
      "23 20 94 43 32\n" +
      "58 40 83 37 75\n" +
      "90 55 27 61 99\n" +
      "\n" +
      "17 60 69 63 39\n" +
      "41 66 23 56 79\n" +
      "57 51 50 28 85\n" +
      "90 20 16 34 81\n" +
      "74 64 46  4 62\n" +
      "\n" +
      "29 55 38 24 11\n" +
      "44 62 49 32 22\n" +
      "81 23 26  0 86\n" +
      "28 25 33  1 88\n" +
      "58 14 97 30 27\n" +
      "\n" +
      "20 69  1 86 77\n" +
      "89 92 15 54 93\n" +
      "94 74 51  5 97\n" +
      "68  8 38 21 46\n" +
      "79 87 40 34 62\n" +
      "\n" +
      "81 49  5 52 77\n" +
      " 3 56 12 60 79\n" +
      "17  7 22 20 34\n" +
      "19 30 18 57 72\n" +
      "97 70 78 69 64\n" +
      "\n" +
      " 2 93 28 54 49\n" +
      "62 81 89  6 67\n" +
      "32  4 42 25 14\n" +
      "24 91 40 77 50\n" +
      "41  5 47 12 95\n" +
      "\n" +
      "63 98 66 51 39\n" +
      "25 46 80 23 18\n" +
      "90 70 24 47 14\n" +
      "15 91 61 85 87\n" +
      "82 92 58 55 40\n" +
      "\n" +
      "94 83 68 23 34\n" +
      "26 35 55 84 97\n" +
      "66 19 85 77 72\n" +
      "73 89 75 42 87\n" +
      "22 40 20 93 67\n" +
      "\n" +
      "53 70 42 54 65\n" +
      "72 24 32 89 83\n" +
      "23 33  5 79 60\n" +
      " 0 61 52 96 49\n" +
      "30 17 94  3 40\n" +
      "\n" +
      "64 65  6 86 53\n" +
      "10 56  2 88 73\n" +
      "11 62 37 84  1\n" +
      "71 42 76 51 77\n" +
      "41 83 19 14  4\n" +
      "\n" +
      "11 26 59 12 66\n" +
      "19 53 52  3 74\n" +
      "65 18 10 40 99\n" +
      "88 61 69 64 92\n" +
      "24 23  9 77 33\n" +
      "\n" +
      "95 81 27 80 63\n" +
      "52 92 18 90 77\n" +
      "34 98 88 41 25\n" +
      "36 49 29 71 61\n" +
      " 6 12 78 15 40\n" +
      "\n" +
      "15  1 48 41  8\n" +
      " 5 74 61 11 78\n" +
      "39 59 63 42 17\n" +
      "23 13 22 88 47\n" +
      " 4 95 33 44  6\n" +
      "\n" +
      "81 16 38 97 74\n" +
      "37 58  2 17 10\n" +
      "99 44  1 20  6\n" +
      "85 68  0 98 95\n" +
      "14 24 93 45 80\n" +
      "\n" +
      "83 39 90 48 38\n" +
      "96 85 84  4 21\n" +
      "55  5 91 33 43\n" +
      "49 37 79 68 20\n" +
      "93 69 54 71 99\n" +
      "\n" +
      "88 69  1 65 20\n" +
      "47 15 57 54 25\n" +
      "44 36 60 99 29\n" +
      "74 46 75 82 40\n" +
      "98 26 77 43 63\n" +
      "\n" +
      "34 48 25 64 32\n" +
      "66  1 71 47 99\n" +
      "21 82 41 14 57\n" +
      " 3 69 28  5 79\n" +
      "27 54 87 44 23\n" +
      "\n" +
      "85 52 77 49 87\n" +
      "92 19  5 37 20\n" +
      "64 69 13 95 55\n" +
      "70 56 86 75 30\n" +
      "73  6 88  9 54\n" +
      "\n" +
      "29 82 35 23 96\n" +
      "11 65 56 86 36\n" +
      "34 83 90 74  2\n" +
      "19 46 61 24 45\n" +
      "63  5 94 48 50\n" +
      "\n" +
      "15 33 21 76 79\n" +
      "28 56 63 38 65\n" +
      "44 41 10 62 60\n" +
      "23 24 16 88 43\n" +
      "30 40 19  7  9\n" +
      "\n" +
      "55 12 27 80 67\n" +
      "42 90 63 10 98\n" +
      "74 50 65 18 17\n" +
      "79  2 96 25 93\n" +
      "24 82 59 56 43\n" +
      "\n" +
      " 7 42 31 49 52\n" +
      "59 50  3 28 58\n" +
      "11 55 75 91 60\n" +
      "65 76  1 38 23\n" +
      "86 19 64 22 80\n" +
      "\n" +
      "73 81 41 54 92\n" +
      "70 39 66 38 27\n" +
      "89 15 80 22 87\n" +
      "36 12 48 93 26\n" +
      "44 85 19 60 49\n" +
      "\n" +
      " 8 28 76 19  5\n" +
      "86 34 50 98 29\n" +
      "80 91 46 90 40\n" +
      "33 58 21 22 49\n" +
      "64 41 87 92 72\n" +
      "\n" +
      "16 61 73 82 25\n" +
      " 8  1 53 10 71\n" +
      "47 99 90 93 39\n" +
      "87 74 41 52 69\n" +
      "17 49 91 30 33";
  private static String numberInput = "73,42,95,35,13,40,99,92,33,30,83,1,36,93,59,90,55,25,77,44,37,62,41,47,80,23,51,61,21,20,76,8,71,34,58,5,52,22,39,57,17,2,26,0,10,72,19,3,64,65,82,46,31,63,91,24,18,12,9,79,50,98,69,4,78,54,43,68,87,7,67,48,28,89,94,53,85,81,49,88,6,96,29,56,97,66,38,16,32,70,74,27,84,86,45,75,60,15,14,11";

  public static void main(String[] args) {
    List<Integer> numbers = new InputParser<Integer>(",")
        .convertInput(numberInput, Integer::parseInt);

    List<BingoCard> bingoCards = new InputParser<BingoCard>("\n\n")
        .convertInput(cardInput, BingoPart2::convertToCard);

    List<String> winnerFingerPrints = new ArrayList<>();
    for (Integer newNumber : numbers) {
      for (BingoCard bingoCard : bingoCards) {
        if (!winnerFingerPrints.contains(bingoCard.fingerprint)) {
          bingoCard.processNumber(newNumber);
          System.out.println("After processing " + newNumber + ", card with fingerPrint " + bingoCard.fingerprint + " looks like this:\n" + bingoCard);
          if (bingoCard.isWinner()) {
            winnerFingerPrints.add(bingoCard.fingerprint);
            System.out.println("Winner found:\n" + bingoCard + " after drawing number " + newNumber);
            if (winnerFingerPrints.size() == bingoCards.size()) { // Everyone's a winner!
              System.out.println("YAY that was the final winner!");
              System.out.println(bingoCard);
              Integer sumOfUncheckedNumbers = bingoCard.bingoNumberRows.stream()
                  .flatMap(Collection::stream)
                  .filter(bingoNumber -> !bingoNumber.isChecked())
                  .map(bingoNumber -> bingoNumber.number)
                  .reduce(0, Integer::sum);
              System.out.println("Total of unchecked numbers = " + sumOfUncheckedNumbers);
              System.out.println("Final score = " + sumOfUncheckedNumbers * newNumber);
            }
          }
        }
      }
    }
  }

  private static BingoCard convertToCard(String bingoCardString) {
    List<String> rows = new InputParser<String>()
        .convertInput(bingoCardString, Function.identity());

    List<List<BingoNumber>> bingoNumberRows = new ArrayList<>();
    rows.forEach(row -> {
      row = row.strip().replace("  ", " "); // fix some formatting
      List<String> numberStrings = Arrays.asList(row.split(" "));
      bingoNumberRows.add(
          numberStrings.stream()
              .map(Integer::parseInt)
              .map(BingoNumber::new)
              .collect(Collectors.toList())
      );
    });

    return new BingoCard(bingoNumberRows);
  }

  private static class BingoCard {
    List<List<BingoNumber>> bingoNumberRows;
    String fingerprint;

    public BingoCard(List<List<BingoNumber>> bingoNumberRows) {
      this.bingoNumberRows = bingoNumberRows;
      fingerprint = bingoNumberRows.stream().flatMap(Collection::stream)
          .map(bingoNumber -> bingoNumber.number.toString())
          .reduce("", (String a, String b) -> a + b);
    }

    void processNumber(Integer newNumber) {
      for (List<BingoNumber> bingoNumberRow : bingoNumberRows) {
        for (BingoNumber bingoNumber : bingoNumberRow) {
          if (bingoNumber.number.equals(newNumber)) {
            bingoNumber.check();
          }
        }
      }
    }

    boolean isWinner() {
      List<List<BingoNumber>> bingoNumberColumns = createColumns(bingoNumberRows);

      for (List<BingoNumber> row : bingoNumberRows) {
        if (isBingo(row)) {
          return true;
        }
      }

      for (List<BingoNumber> column : bingoNumberColumns) {
        if (isBingo(column)) {
          return true;
        }
      }

      return false;
    }

    private boolean isBingo(List<BingoNumber> numbers) {
      return numbers.stream()
          .map(BingoNumber::isChecked)
          .reduce(Boolean.TRUE, (input, result) -> input && result);
    }

    private List<List<BingoNumber>> createColumns(List<List<BingoNumber>> bingoNumberRows) {
      List<List<BingoNumber>> bingoNumberColumns = new ArrayList<>();
      IntStream.iterate(0, i -> i + 1).limit(bingoNumberRows.size()).forEach(position -> {
        List<BingoNumber> column = bingoNumberRows.stream()
            .map(row -> row.get(position))
            .collect(Collectors.toList());
        bingoNumberColumns.add(column);
      });
      System.out.println("Columns look like this:\n" + new BingoCard(bingoNumberColumns));
      return bingoNumberColumns;
    }

    @Override
    public String toString() {
      StringBuilder representation = new StringBuilder();
      bingoNumberRows.forEach(
          row -> {
            row.forEach(representation::append);
            representation.append("\n");
          }
      );
      return representation.toString();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      BingoCard bingoCard = (BingoCard) o;
      return fingerprint.equals(bingoCard.fingerprint);
    }

    @Override
    public int hashCode() {
      return Objects.hash(fingerprint);
    }
  }

  public static class BingoNumber {
    Integer number;
    boolean checked = false;

    public BingoNumber(Integer number) {
      this.number = number;
    }

    void check() {
      checked = true;
    }

    public boolean isChecked() {
      return checked;
    }

    @Override
    public String toString() {
      String representation = number + "";
      if (checked) {
        representation = "(" + representation + ")";
      }
      representation += "    "; // buffer
      representation = representation.substring(0, 5); //cut off
      return representation;
    }
  }
}

