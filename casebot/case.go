package main

import "fmt"
import "strings"

func what_beats(move string, i int) string {
    winners := map[string][2]string{
        "rock": [2]string{"paper","spock"},
        "paper": [2]string{"scissors","lizard"},
        "scissors": [2]string{"rock","spock"},
        "lizard": [2]string{"rock","scissors"},
        "spock": [2]string{"paper","lizard"},
    }

    first := i%2
    return winners[move][first]
}

func find_patterns(history []string) []string {
    lookback := 5
    if len(history) < 5 { 
        lookback = len(history) 
    }

    var newpattern []string
    var patterns []string
    for i := 1; i <= lookback; i++  {
        p := history[len(history)-i]
        newpattern = append(newpattern, p)
        patterns = append(patterns, strings.Join(newpattern, ","))
    }
    return patterns
}
func run() {
    patterns := map[string]string{}
    var history []string 

    var mymove string
    var opponent string
    count := 0
    for opponent != "goodbye" {
        
        mymove = "spock"
        
        history_patterns := find_patterns(history)
        for _,p := range history_patterns {
            expected, ok := patterns[p]
            //fmt.Println(p + "..." + expected)
            if ok {
                mymove = what_beats(expected, count)
            }
        }
        fmt.Println(mymove)
        fmt.Scanln(&opponent)

        for _,p := range history_patterns {
            patterns[p] = opponent
        }

        history = append(history, mymove+"|"+opponent)
        count++
    }
}

func main() {
    run()
}

