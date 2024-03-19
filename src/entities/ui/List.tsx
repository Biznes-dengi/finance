import {ReactNode} from 'react';

import {textHelpers} from '@shared/lib';
import {EmptyState} from '@shared/ui/EmptyState.tsx';
import {APP_TEXT} from '@shared/constants';

const {getDontHaveAny} = textHelpers;

type Props<R> = {
	rows: R[];
	renderRow: (row: R) => ReactNode;
};

export function List<R>(props: Props<R>) {
	const {rows, renderRow} = props;

	return (
		<>
			<div className='px-4 py-3 '>
				<div className='font-medium text-primary-grey'>{APP_TEXT.items}</div>
			</div>

			<EmptyState text={getDontHaveAny(APP_TEXT.goal)} visible={!rows.length} />

			{rows.map((row) => {
				return renderRow(row);
			})}
		</>
	);
}
