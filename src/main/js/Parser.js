import * as espree from "espree";

export class Parser {
    parse(code) {
        return espree.parse(code);
    }
}
