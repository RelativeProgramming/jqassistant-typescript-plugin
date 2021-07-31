import * as espree from "espree";

export class Parser {
    parse(code, ecmaVersion, sourceType) {
        let options = {ecmaVersion: ecmaVersion, sourceType: sourceType};
        return espree.parse(code, options);
    }
}
